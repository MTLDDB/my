package mapper;

import entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DigikeyMapper {
    void saveCategory(@Param("c") Category category);

    List<Category> getUrlList();
}
