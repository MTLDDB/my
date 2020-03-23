package mapper;

import entity.Category;
import entity.Detailedinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DigikeyMapper {
    void saveCategory(@Param("c") Category category);

    List<Category> getUrlList();

    @Select("select * from tt_detailedinfo where mfr =#{mfr};")
    List<Detailedinfo> getMfr(@Param("mfr") String mfr);
    @Select("select * from tt_detailedinfo where objectid =#{id};")
    Detailedinfo getProductById(@Param("id") String id);
    @Select("select price_json from tt_price where detail_id=#{id};")
    String getPrice(@Param("id") String  id);
    @Select("select attributes_json from tt_attributes where objectid=#{id}")
    String getAttr(@Param("id") String id);
}
