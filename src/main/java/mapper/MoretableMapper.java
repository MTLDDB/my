package mapper;

import entity.User;
import org.apache.ibatis.annotations.Param;

public interface MoretableMapper {
    User selectUserById(@Param("id") int id);
}
