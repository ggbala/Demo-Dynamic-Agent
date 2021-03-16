/**
 * 老师接口的实现类，具体实现 teach() 方法
 */
public class RealTeacher implements Teacher {
    @Override
    public void teach() {
        // 老师正在教书
        System.out.println("I am a teacher, I am teaching!");
    }
}