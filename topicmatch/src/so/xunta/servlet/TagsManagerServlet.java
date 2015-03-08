package so.xunta.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import so.xunta.entity.Tag;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.utils.HtmlRegexpUtil;

public class TagsManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TagsManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId = Long.parseLong(new String(request.getParameter("userId").getBytes("ISO8859-1"),"UTF-8")); 
		String tags =  new String(request.getParameter("tags").getBytes("ISO8859-1"),"UTF-8"); 
		String finalTags = HtmlRegexpUtil.filterHtml(tags).replaceAll("", "");
		String tagArray[] = finalTags.split(",");
		List<Tag> list = new ArrayList<Tag>();
		for (int i = 0; i < tagArray.length; i++) {
			String tag = tagArray[i];
			System.out.println("将标签存入数据库  ==>   userId : "+userId+"  --   tag : "+tag);
			list.add(new Tag(userId, tag));
		}
		new TagsManagerImpl().addTags(list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
