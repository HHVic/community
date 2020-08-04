package cn.huan.community.community.cache;

import cn.huan.community.community.domain.Tag;
import cn.huan.community.community.dto.TagDTO;
import cn.huan.community.community.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {

    public static List<TagDTO> getFromMysql(List<Tag> tags) {
        List<TagDTO> tagDTOS = new ArrayList<>();
        //获取所有categoryName
        List<String> categoryNames = tags.stream().filter(s -> s.getParentTagName() == null).map(tag -> tag.getTagName()).collect(Collectors.toList());
        //过滤获取所有tag
        List<Tag> tagList = tags.stream().filter(s -> s.getParentTagName() != null).collect(Collectors.toList());
        for (String categoryName : categoryNames) {
            List<String> collect = tags.stream()
                    .filter(s -> categoryName.equals(s.getParentTagName()))
                    .map(s -> s.getTagName())
                    .collect(Collectors.toList());
            TagDTO tool = new TagDTO();
            tool.setCategoryName(categoryName);
            tool.setTags(collect);
            tagDTOS.add(tool);
        }

        return tagDTOS;
    }

    public static void main(String[] args) {
        List<TagDTO> tagDTOS = get();
        System.out.println(tagDTOS);
    }

    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDTOS.add(framework);


        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagDTOS.add(tool);
        return tagDTOS;
    }

    public static String filterInvalid(List<Tag> DBtags, String tags){
        String[] split = tags.split(",");
        List<TagDTO> tagDTOS = getFromMysql(DBtags);
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
