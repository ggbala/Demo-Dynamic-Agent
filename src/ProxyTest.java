import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) throws Throwable {
        // 这里能清楚地看到，目标对象 RealTeacher 是一个纯粹的参数
        Teacher teacher = (Teacher)getProxy(new RealTeacher());
        // teacher 是接口 Teacher 的一个实现类，
        // 完全从多态的角度也能想到执行的实际上是 RealTeacher 的 teach() 方法
        teacher.teach();
    }
    public static Object getProxy(Object target) throws Throwable {

        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        System.out.println("Do something before1");
                        result = method.invoke(target, args);
                        System.out.println("Do something after1");
                        return result;
                    }
        });
    }
}
