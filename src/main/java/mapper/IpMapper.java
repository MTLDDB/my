package mapper;
import entity.IP;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IpMapper {
    //void insertIpList(@Param("list") List<IP> list);
    void insertIp(@Param("p") IP p);
    IP selectOne(@Param("ip") String ip);
    void update(@Param("p") IP p);

}
