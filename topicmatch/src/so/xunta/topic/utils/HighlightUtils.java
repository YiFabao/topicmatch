package so.xunta.topic.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.wltea.analyzer.lucene.IKAnalyzer;

import so.xunta.entity.Tag;
import so.xunta.manager.TagsManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.topic.entity.Topic;
import so.xunta.utils.HibernateUtils;

public class HighlightUtils {
	//准备分词器
	static Analyzer analyzer = new IKAnalyzer();// IK分词器
    //准备query
	TagsManager tagmanager = new TagsManagerImpl();
	
	public static String getHighlightContentByInput(List<Tag> tagslist,String content) throws IOException, InvalidTokenOffsetsException{
		

		List<Topic> topicList = new ArrayList<>();

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
		// 准备高亮器  
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");  
        Scorer scorer = new QueryScorer(query);  
        Highlighter highlighter = new Highlighter(formatter, scorer);  
  
        Fragmenter fragmenter = new SimpleFragmenter(100);// 指定100个字符  
        highlighter.setTextFragmenter(fragmenter);// 决定是否生成摘要，以及摘要有多长  
        
        String highlighterStr = highlighter.getBestFragment(analyzer,  
                "topicName", content);
       // System.out.println(highlighterStr);
		return highlighterStr;
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
			//System.out.print(term + "\t");
			q.add(term.toString());
		}
		return q;
	}
	
	public static void main(String[] args) throws IOException, InvalidTokenOffsetsException {
		List<Tag> ls = new ArrayList<Tag>();
		Tag tag = new Tag(1l, "上海", "asdf");
		ls.add(tag);
		HighlightUtils.getHighlightContentByInput(ls, "上海哪好玩啊");
	}
}


