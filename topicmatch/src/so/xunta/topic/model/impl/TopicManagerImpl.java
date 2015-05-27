package so.xunta.topic.model.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.wltea.analyzer.lucene.IKAnalyzer;

import so.xunta.entity.Tag;
import so.xunta.localcontext.LocalContext;
import so.xunta.manager.TagsManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.topic.entity.MessageAlert;
import so.xunta.topic.entity.RecommendedPeople;
import so.xunta.topic.entity.RecommendedTopicPublisher;
import so.xunta.topic.entity.Topic;
import so.xunta.topic.entity.TopicGroup;
import so.xunta.topic.entity.TopicHistory;
import so.xunta.topic.model.TopicManager;
import so.xunta.topic.model.TopicModel;
import so.xunta.utils.HibernateUtils;
import so.xunta.websocket.entity.HistoryMessage;
import weibo4j.org.json.JSONArray;

public class TopicManagerImpl implements TopicManager {
	static Analyzer analyzer = new IKAnalyzer();// IK分词器
	Directory directory = null;

	@Override
	public List<Topic> matchMyTopic(String mytopic) {
		List<Topic> topicList = new ArrayList<>();
		try {
			List<String> q = showTerms(mytopic, analyzer);
			BooleanQuery query = new BooleanQuery();
			for (String t : q) {
				// 没有同义词
				TermQuery tq1 = new TermQuery(new Term("topicContent", t));
				TermQuery tq2 = new TermQuery(new Term("topicName", t));
				query.add(tq1, Occur.SHOULD);
				query.add(tq2, Occur.SHOULD);

			}
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, Integer.MAX_VALUE).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// 匹配的话题
				String topicContent = searcher.doc(docID).get("topicContent");
				// 话题名
				String topicName = searcher.doc(docID).get("topicName");
				// 用户id
				String userId = searcher.doc(docID).get("userId");
				// 用户名
				String userName = searcher.doc(docID).get("userName");
				// 日期
				String createTime = searcher.doc(docID).get("createTime");
				// 匹配的话题高亮
				String hightLightTopic = highLighter(topicContent, query, analyzer, 10, 10);

				Topic topic = new Topic(topicId, userId, userName, topicName, topicContent, "", createTime, "");
				topicList.add(topic);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	}

	@Override
	public List<String> matchMyTopicIds(String mytopic) {
		List<String> topicList = new ArrayList<>();
		try {
			List<String> q = showTerms(mytopic, analyzer);
			BooleanQuery query = new BooleanQuery();
			for (String t : q) {
				// 没有同义词
				TermQuery tq1 = new TermQuery(new Term("topicContent", t));
				TermQuery tq2 = new TermQuery(new Term("topicName", t));
				query.add(tq1, Occur.SHOULD);
				query.add(tq2, Occur.SHOULD);

			}
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, Integer.MAX_VALUE).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// // 匹配的话题
				// String topicContent =
				// searcher.doc(docID).get("topicContent");
				// // 话题名
				// String topicName = searcher.doc(docID).get("topicName");
				// // 用户id
				// String userId = searcher.doc(docID).get("userId");
				// // 用户名
				// String userName = searcher.doc(docID).get("userName");
				// // 日期
				// String createTime = searcher.doc(docID).get("createTime");
				// // 匹配的话题高亮
				// String hightLightTopic = highLighter(topicContent, query,
				// analyzer, 10, 10);
				//
				// Topic topic = new Topic(topicId, userId, userName, topicName,
				// topicContent, "", createTime, "");
				topicList.add(topicId);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	};

	@Override
	public List<Topic> matchMyTopic(String _topicName, String mytopic) {
		List<Topic> topicList = new ArrayList<>();
		try {
			List<String> q = showTerms(mytopic, analyzer);
			List<String> q2 = showTerms(_topicName, analyzer);
			q.addAll(q2);
			BooleanQuery query = new BooleanQuery();
			for (String t : q) {
				// 没有同义词
				TermQuery tq1 = new TermQuery(new Term("topicContent", t));
				TermQuery tq2 = new TermQuery(new Term("topicName", t));
				query.add(tq1, Occur.SHOULD);
				query.add(tq2, Occur.SHOULD);

			}
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, Integer.MAX_VALUE).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// 匹配的话题
				String topicContent = searcher.doc(docID).get("topicContent");
				// 话题名
				String topicName = searcher.doc(docID).get("topicName");
				// 用户id
				String userId = searcher.doc(docID).get("userId");
				// 用户名
				String userName = searcher.doc(docID).get("userName");
				// 日期
				String createTime = searcher.doc(docID).get("createTime");
				// 匹配的话题高亮
				String hightLightTopic = highLighter(topicContent, query, analyzer, 10, 10);

				Topic topic = new Topic(topicId, userId, userName, topicName, topicContent, "", createTime, "");
				topicList.add(topic);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	}

	@Override
	public List<Topic> matchUserRelativeTopic(String userId, String topicContent) {
		List<Topic> topicList = new ArrayList<>();
		try {
			List<String> q = showTerms(topicContent, analyzer);
			BooleanQuery query = new BooleanQuery();
			TermQuery termquery = new TermQuery(new Term("userId", userId));
			for (String t : q) {
				BooleanQuery bq = new BooleanQuery();

				TermQuery tq1 = new TermQuery(new Term("topicContent", t));
				bq.add(tq1, Occur.MUST);
				bq.add(termquery, Occur.MUST);
				query.add(bq, Occur.SHOULD);
			}
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, Integer.MAX_VALUE).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// 匹配的话题
				String _topicContent = searcher.doc(docID).get("topicContent");
				// 话题名
				String topicName = searcher.doc(docID).get("topicName");
				// 用户id
				String _userId = searcher.doc(docID).get("userId");
				// 用户名
				String userName = searcher.doc(docID).get("userName");
				// 日期
				String createTime = searcher.doc(docID).get("createTime");
				// 匹配的话题高亮
				String hightLightTopic = highLighter(topicContent, query, analyzer, 10, 10);

				Topic topic = new Topic(topicId, _userId, userName, topicName, _topicContent, "", createTime, "");
				topicList.add(topic);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	}

	@Override
	public List<Topic> matchUserRelativeTopic(String userId, String _topicName, String topicContent) {
		List<Topic> topicList = new ArrayList<>();
		try {
			List<String> q = showTerms(topicContent, analyzer);
			List<String> q2 = showTerms(_topicName, analyzer);
			q.addAll(q2);
			BooleanQuery query = new BooleanQuery();
			TermQuery termquery = new TermQuery(new Term("userId", userId));
			for (String t : q) {
				BooleanQuery bq = new BooleanQuery();
				BooleanQuery topicBooleanQuery = new BooleanQuery();
				TermQuery tq1 = new TermQuery(new Term("topicContent", t));
				TermQuery tq2 = new TermQuery(new Term("topicName", t));
				topicBooleanQuery.add(tq1, Occur.SHOULD);
				topicBooleanQuery.add(tq2, Occur.SHOULD);

				bq.add(topicBooleanQuery, Occur.MUST);
				bq.add(termquery, Occur.MUST);
				query.add(bq, Occur.SHOULD);
			}
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, Integer.MAX_VALUE).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// 匹配的话题
				String _topicContent = searcher.doc(docID).get("topicContent");
				// 话题名
				String topicName = searcher.doc(docID).get("topicName");
				// 用户id
				String _userId = searcher.doc(docID).get("userId");
				// 用户名
				String userName = searcher.doc(docID).get("userName");
				// 日期
				String createTime = searcher.doc(docID).get("createTime");
				// 匹配的话题高亮
				String hightLightTopic = highLighter(topicContent, query, analyzer, 10, 10);

				Topic topic = new Topic(topicId, _userId, userName, topicName, _topicContent, "", createTime, "");
				topicList.add(topic);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	}

	/**
	 * 分词
	 * 
	 * @param topic
	 * @param analyzer
	 * @return
	 * @throws IOException
	 */
	private static List<String> showTerms(String topic, Analyzer analyzer) throws IOException {
		TokenStream tokenStream = analyzer.tokenStream("topic", new StringReader(topic));

		CharTermAttribute term = tokenStream.addAttribute(CharTermAttribute.class);

		tokenStream.reset();

		List<String> q = new ArrayList<String>();
		while (tokenStream.incrementToken()) {
			// System.out.print(term + "\t");
			q.add(term.toString());
		}
		return q;
	}

	/**
	 * 高度
	 * 
	 * @param textToBeHighLighted
	 * @param query
	 * @param analyzer
	 * @param numOfHighlightWords
	 * @param numOfHightlightedKeywords
	 * @return
	 */
	public static String highLighter(String textToBeHighLighted, Query query, Analyzer analyzer, int numOfHighlightWords, int numOfHightlightedKeywords) {
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, numOfHighlightWords);// 后一个参数是加亮前后的保留字数
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
		Highlighter hig = new Highlighter(formatter, scorer);
		hig.setTextFragmenter(fragmenter);
		TokenStream tokens = null;
		String highterResult = null;
		try {
			tokens = analyzer.tokenStream("index_content", new StringReader(textToBeHighLighted));
			highterResult = hig.getBestFragments(tokens, textToBeHighLighted, numOfHightlightedKeywords, "...");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		return highterResult;
	}

	@Override
	public void createTopicIndex(Topic topic) {
		try {
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
			IndexWriter iwriter = new IndexWriter(directory, config);

			Document doc = new Document();

			FieldType fieldType1 = new FieldType();
			fieldType1.setIndexed(true);
			fieldType1.setStored(true);
			fieldType1.storeTermVectors();
			fieldType1.storeTermVectorPositions();
			fieldType1.storeTermVectorPayloads();

			FieldType fieldType2 = new FieldType();
			fieldType2.setIndexed(true);
			fieldType2.setStored(true);
			fieldType2.setTokenized(false);

			doc.add(new Field("userId", topic.userId, fieldType2));
			doc.add(new Field("userName", topic.userName, fieldType2));
			doc.add(new Field("topicId", topic.topicId, fieldType2));
			doc.add(new Field("topicName", topic.topicName, fieldType1));
			doc.add(new Field("topicContent", topic.topicContent, fieldType1));
			doc.add(new Field("createTime", topic.createTime, fieldType2));
			iwriter.addDocument(doc);
			iwriter.commit();
			iwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Topic> matchMyTopicByUserId(String authorId) {
		List<Topic> topicList = new ArrayList<>();
		try {
			TermQuery query = new TermQuery(new Term("authorId", authorId));
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, 100).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// 匹配的话题
				String topicContent = searcher.doc(docID).get("topicContent");
				// 作者名
				String topicAuthor = searcher.doc(docID).get("topicAuthorName");
				// 作者id
				String topicAuthorId = searcher.doc(docID).get("authorId");

				// 日期
				String datetime = searcher.doc(docID).get("topicCreatetime");

				// (String topicId,String authorId,String hightLightTopic,
				// String authorName,String topicCreatetime)
				// Topic topicObj = new
				// Topic(topicId,topicAuthorId,topicContent,topicAuthor,datetime);

				// topicList.add(topicObj);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	}

	@Override
	public void saveTopic(Topic topic) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(topic);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void saveTopicGroup(TopicGroup topicMember) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(topicMember);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			System.out.println("异常："+e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public List<MessageAlert> searchMyMessage(String authorId) {
		// TODO 从数据库中查询出自己的消息
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from MessageAlert as m where m.authorId=? and m.isHandle = 0";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, authorId);
			List<MessageAlert> l = query.list();
			Collections.sort(l);
			session.getTransaction().commit();
			return l;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void addMessageAlert(MessageAlert messageAlert) {
		// System.out.println("添加消息提醒");
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(messageAlert);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void addTopicHistory(TopicHistory topicHistory) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(topicHistory);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public long searchNotReadmessageNum(String authorId) {
		// System.out.println("查询未读消息数");
		Session session = HibernateUtils.openSession();
		try {

			session.beginTransaction();
			String hql = "select count(*) from SysMessage m where m.toUserId=? and m.isHandle=0";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, authorId);
			long num = (Long) query.uniqueResult();
			session.getTransaction().commit();
			return num;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean checkIsTopicMember(String memberId, String topicId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			org.hibernate.Query query = session.createQuery("from TopicGroup as tg where tg.topicMemberId=? and tg.topicId = ?");
			query.setString(0, memberId);
			query.setString(1, topicId);
			List<TopicGroup> topicMemberList = query.list();
			session.getTransaction().commit();
			if (topicMemberList.size() > 0) {
				return true;
			}
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<TopicGroup> searchTopicMemberList(String topicId) {

		List<TopicGroup> topicMembers = new ArrayList<TopicGroup>();
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from TopicGroup tg where tg.topicId=?";
			org.hibernate.Query query = session.createQuery(hql);
			topicMembers = query.setString(0, topicId).list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		return topicMembers;
	}

	@Override
	public String searchTopicContent(String topicId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "select topicContent from Topic t where t.topicId=?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, topicId);
			String topicContent = (String) query.uniqueResult();
			session.getTransaction().commit();
			return topicContent;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public Topic searchLatestTopic(String authorId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from Topic t where t.authorId=? order by t.topicCreatetime desc";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, authorId);
			query.setMaxResults(1);
			Topic topic = (Topic) query.uniqueResult();
			session.getTransaction().commit();
			return topic;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Topic> searchTopicHistory(String authorId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String sql = "select topic.* from topic,topichistory where topichistory.authorId=? and topic.topicId=topichistory.topicId ";
			SQLQuery sqlquery = session.createSQLQuery(sql);
			sqlquery.addEntity(Topic.class);
			sqlquery.setString(0, authorId);
			List<Topic> topicList = sqlquery.list();
			session.getTransaction().commit();
			Collections.sort(topicList);
			return topicList;

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void updateMessageAlertToAlreadyRead(String authorId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "update MessageAlert as ma set ma.isRead=1 where ma.authorId=?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, authorId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean checkTopicIsExitInHistory(String authorId, String topicId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from TopicHistory t where t.authorId=? and t.topicId=?";
			org.hibernate.Query query = session.createQuery(hql);

			query.setString(0, authorId);
			query.setString(1, topicId);

			List<TopicHistory> topicHistoryList = query.list();
			session.getTransaction().commit();
			if (topicHistoryList.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public String searchNicknameByUserId(int userId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "select xunta_username from User where id = ?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger(0, userId);
			String nickname = (String) query.uniqueResult();
			session.getTransaction().commit();
			return nickname;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteOneMessage(int id) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "delete MessageAlert as m where m.id = ?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger(0, id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void updateMessageAlertToAlreadyHandle(int pid) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "update MessageAlert as ma set ma.isHandle=1 where id=?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger(0, pid);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Topic> searchMyTopicHistory(String userId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from Topic as t where t.userId =? ";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, userId);
			List<Topic> topicList = query.list();
			session.getTransaction().commit();
			return topicList;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Topic> searhMyJoinTopic(String userId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String sql = "select t.* from topic  as t where t.topicId in (select th.topicId from topichistory as th where th.authorId=? and th.publish_or_join = 'j')";
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, userId);
			query.addEntity(Topic.class);
			List<Topic> topicList = query.list();
			session.getTransaction().commit();
			return topicList;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public Topic findTopicByTopicId(String topicId) {
		Session session = HibernateUtils.openSession();
		String hql = "from Topic as t where t.topicId = ? ";
		org.hibernate.Query query = session.createQuery(hql);
		query.setString(0, topicId);
		Topic topic = (Topic) query.uniqueResult();
		session.close();
		return topic;
	}

	@Override
	public List<String> findMemberIdsByTopicId(String topicId) {
		Session session = HibernateUtils.openSession();
		String hql = "select topicMemberId from TopicGroup as tg where tg.topicId = ?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setString(0, topicId);
		List<String> memberIds = query.list();
		session.close();
		return memberIds;
	}

	@Override
	public List<Topic> getTopicListByTopicIdList(List<String> topicIdList) {
		Session session = HibernateUtils.openSession();
		if (topicIdList == null || topicIdList.size() == 0) {
			return null;
		}
		String hql = "from Topic as t where t.topicId in (:topicIdList)";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameterList("topicIdList", topicIdList);
		List<Topic> topicList = query.list();
		session.close();
		return topicList;
	}

	@Override
	public List<TopicHistory> findTopicHistoryByTopicId(List<String> topicIdList) {
		if (topicIdList.size() == 0) {
			return null;
		}
		Session session = HibernateUtils.openSession();
		String hql = "from TopicHistory as t where t.topicId in (:topicIdList)";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameterList("topicIdList", topicIdList);

		List<TopicHistory> TopicHistoryList = query.list();
		session.close();
		return TopicHistoryList;
	}

	@Override
	public void addTopicJoinNumByOne(String topicId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from Topic where topicId=?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, topicId);
			Topic topic = (Topic) query.uniqueResult();
			topic.setJoin_people_num(topic.getJoin_people_num() + 1);
			session.update(topic);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Topic> recommendTopics(String userId2) {
		// 根据userId查询出 该用户的标签
		TagsManager tagmanager = new TagsManagerImpl();
		List<Tag> tagslist = tagmanager.findAllTagsByUserId(Long.parseLong(userId2));
		/*
		 * for (Tag tag : tagslist) { System.out.println(tag.getTagname()); }
		 */
		List<Topic> topicList = new ArrayList<>();
		try {
			BooleanQuery query = new BooleanQuery();
			for (Tag tag : tagslist) {
				List<String> q = showTerms(tag.getTagname(), analyzer);
				for (String t : q) {
					// 没有同义词
					TermQuery tq1 = new TermQuery(new Term("topicContent", t));
					TermQuery tq2 = new TermQuery(new Term("topicName", t));
					query.add(tq1, Occur.SHOULD);
					query.add(tq2, Occur.SHOULD);

				}
			}
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, Integer.MAX_VALUE).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// 匹配的话题
				String topicContent = searcher.doc(docID).get("topicContent");
				// 话题名
				String topicName = searcher.doc(docID).get("topicName");
				// 用户id
				String userId = searcher.doc(docID).get("userId");
				if (userId.equals(userId2)) {
					continue;
				}
				// 用户名
				String userName = searcher.doc(docID).get("userName");
				// 日期
				String createTime = searcher.doc(docID).get("createTime");
				// 匹配的话题高亮
				String hightLightTopic = highLighter(topicContent, query, analyzer, 10, 10);

				Topic topic = new Topic(topicId, userId, userName, topicName, hightLightTopic, "", createTime, "");
				topicList.add(topic);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	}

	@Override
	// fang
	public List<TopicHistory> findAuthorIdAndPublish_or_joinByTopicId(String authorId, String publish_or_join, int titleNum) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from TopicHistory where authorId=? and publish_or_join=? order by datetime desc";
			org.hibernate.Query query = session.createQuery(hql).setFirstResult(titleNum).setMaxResults(10);
			query.setString(0, authorId);
			query.setString(1, publish_or_join);
			List<TopicHistory> topicIdList = query.list();
			session.getTransaction().commit();
			return topicIdList;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	// fang
	public String findTopicIdByTopicHistory(String topicId, String userId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "select datetime from TopicHistory where topicId=? and authorId=?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, topicId);
			query.setString(1, userId);
			String datetime = (String) query.uniqueResult();
			session.getTransaction().commit();
			return datetime;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<String> matchMyTopicIds(String _topicName, String mytopic) {
		List<String> topicList = new ArrayList<>();
		try {
			List<String> q = showTerms(mytopic, analyzer);
			List<String> q2 = showTerms(_topicName, analyzer);
			q.addAll(q2);
			BooleanQuery query = new BooleanQuery();
			for (String t : q) {
				// 没有同义词
				TermQuery tq1 = new TermQuery(new Term("topicContent", t));
				TermQuery tq2 = new TermQuery(new Term("topicName", t));
				query.add(tq1, Occur.SHOULD);
				query.add(tq2, Occur.SHOULD);

			}
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			ScoreDoc[] hits = searcher.search(query, Integer.MAX_VALUE).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				// 话题唯一id
				String topicId = searcher.doc(docID).get("topicId");
				// //匹配的话题
				// String topicContent =
				// searcher.doc(docID).get("topicContent");
				// //话题名
				// String topicName = searcher.doc(docID).get("topicName");
				// //用户id
				// String userId = searcher.doc(docID).get("userId");
				// //用户名
				// String userName = searcher.doc(docID).get("userName");
				// //日期
				// String createTime = searcher.doc(docID).get("createTime");
				// //匹配的话题高亮
				// String hightLightTopic = highLighter(topicContent, query,
				// analyzer, 10, 10);
				//
				// Topic topic = new Topic(topicId, userId, userName, topicName,
				// topicContent,"", createTime,"");
				topicList.add(topicId);
			}
			ireader.close();// 关闭ireader
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(topicList);
		return topicList;
	}

	@Override
	// fang
	public LinkedHashMap<String, HistoryMessage> findTopicIdByHistoryMessage(List<TopicHistory> list) {
		LinkedHashMap<String, HistoryMessage> historyMessageMap = new LinkedHashMap<String, HistoryMessage>();
		for (TopicHistory topicHistory : list) {
			Session session = HibernateUtils.openSession();
			String topicId = topicHistory.getTopicId();
			try {
				session.beginTransaction();
				String hql = "from HistoryMessage where topicId = ? order by dateandtime desc";
				org.hibernate.Query query = session.createQuery(hql);
				query.setString(0, topicId);
				if (query.list().size() == 0) {
					historyMessageMap.put(topicId, null);
				} else {
					historyMessageMap.put(topicId, (HistoryMessage) query.list().get(0));
				}
				session.getTransaction().commit();
			} catch (RuntimeException e) {
				session.getTransaction().rollback();
				throw e;
			} finally {
				session.close();
			}
		}
		return historyMessageMap;
	};

	@Override
	// fang
	public Topic findTopicIdByTopic(String topicId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from Topic where topicId = ? ";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, topicId);
			Topic topic = (Topic) query.uniqueResult();
			session.getTransaction().commit();
			return topic;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	// Yanyu Li
	public List<String> findTopicIdListByCreaterId(String userId) {
		Session session = HibernateUtils.openSession();
		if (userId == null || "".equals(userId.trim())) {
			return null;
		}
		String hql = "select topicId from Topic as t where t.userId = ?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setString(0, userId.trim());
		List<String> topicIdList = query.list();
		session.close();
		return topicIdList;
	}

	@Override
	// Yanyu Li
	public void updateCreaterImg(List<String> userCreatedTopicIds, String newimgName) {
		if (userCreatedTopicIds.size() == 0)
			return;
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "update Topic as t set t.logo_url=? where t.topicId in (:topicIdList)";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, newimgName);
			query.setParameterList("topicIdList", userCreatedTopicIds);

			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void updateCreaterUsername(List<String> userCreatedTopicIds, String userNameR) {
		if (userCreatedTopicIds.size() == 0)
			return;
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "update Topic as t set t.userName=? where t.topicId in (:topicIdList)";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, userNameR);
			query.setParameterList("topicIdList", userCreatedTopicIds);

			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void updateLastUpdateTime(String topicId, String datetime) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "update Topic as t set t.lastUpdateTime=? where t.topicId =?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, datetime);
			query.setString(1, topicId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Topic> getLatestUpdateTopics(int num) {
		Session session = HibernateUtils.openSession();
		String hql = "from Topic as t order by t.lastUpdateTime desc";
		org.hibernate.Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(num);
		return query.list();
	}

	public static void main(String[] args) {
		TopicManager topicmanager = new TopicManagerImpl();
		TopicModel topicModel = new TopicModelImpl();
		List<Topic> topicList = topicmanager.getLatestUpdateTopics(1);
		for (Topic t : topicList) {
			System.out.println(URLDecoder.decode(t.lastResMsg));
		}

	}

	@Override
	public void updateLastResMsgAndTime(String topicId, String resMsg, String datetime) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from Topic as t where t.topicId =?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, topicId);
			Topic topic = (Topic) query.uniqueResult();
			if (topic == null) {
				System.err.println("更新话题最后回复时,查询话题为空");
			} else {
				topic.setLastUpdateTime(datetime);
				topic.setLastResMsg(resMsg);
				session.update(topic);
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void saveTopicList(List<Topic> topicList) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		try {
			for (int i = 0; i < topicList.size(); i++) {
				session.save(topicList.get(i));
				if (i % 10 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void saveTopicGroupList(List<TopicGroup> topicGroupList) {
		for (int i = 0; i < topicGroupList.size(); i++) {
			try {
				saveTopicGroup(topicGroupList.get(i));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void addTopicHistoryList(List<TopicHistory> topicHistory) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			for (int i = 0; i < topicHistory.size(); i++) {
				session.save(topicHistory.get(i));
				if (i % 10 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void createTopicListIndex(List<Topic> topicList) {
		try {
			if (directory == null) {
				directory = FSDirectory.open(new File(LocalContext.getIndexFilePath()));
			}
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
			IndexWriter iwriter = new IndexWriter(directory, config);
			Document doc = new Document();

			FieldType fieldType1 = new FieldType();
			fieldType1.setIndexed(true);
			fieldType1.setStored(true);
			fieldType1.storeTermVectors();
			fieldType1.storeTermVectorPositions();
			fieldType1.storeTermVectorPayloads();

			FieldType fieldType2 = new FieldType();
			fieldType2.setIndexed(true);
			fieldType2.setStored(true);
			fieldType2.setTokenized(false);

			for (Topic topic : topicList) {
				doc.add(new Field("userId", topic.userId, fieldType2));
				doc.add(new Field("userName", topic.userName, fieldType2));
				doc.add(new Field("topicId", topic.topicId, fieldType2));
				doc.add(new Field("topicName", topic.topicName, fieldType1));
				doc.add(new Field("topicContent", topic.topicContent, fieldType1));
				doc.add(new Field("createTime", topic.createTime, fieldType2));
				iwriter.addDocument(doc);
			}
			iwriter.commit();
			iwriter.close();
		} catch (IOException e) {
			System.out.println("异常:"+e.getMessage());
		}

	}

	@Override
	public List<Topic> findTopicsByTopicNameList(List<String> topicNameList) {
		Session session = HibernateUtils.openSession();
		String hql = "from Topic as t where t.topicName in (:topicnameList) ";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameterList("topicnameList", topicNameList);
		List<Topic> topicList = query.list();
		session.close();
		return topicList;
	}
}
