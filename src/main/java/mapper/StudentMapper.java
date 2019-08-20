package mapper;
import entity.Clazz;
import entity.Student;
import org.apache.ibatis.annotations.*;


import java.util.List;
public interface StudentMapper {
    //查询用户列表
    List<Student> findUserByStudentname(String studentname) throws Exception;
    //根据用户id查询用户信息
    Student findUserById(int id) throws Exception;
    Student selectOne() throws Exception;
    //List<Student> selectAll1();
    void insertStudent(@Param("stu") Student stu);

    @Delete("delete from student where student_id=#{id}")
    void deleteById(@Param("id") Integer id);

    @Update("update student set name1=#{stu.name1} where student_id=#{stu.studentId}")
    void updateById(@Param("stu") Student stu);

    //@ResultMap("BaseResultMap")
    //@Select("select * from student")
    List<Student> selectStudent();

    Clazz selectClazzById(@Param("id") int id);
}
