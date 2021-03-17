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

        System.out.println(teacher.getClass().getSuperclass());//输出是class java.lang.reflect.Proxy

        Class<?>[] interfaces = teacher.getClass().getInterfaces();

        for(Class<?> i : interfaces){
            System.out.println(i);// 输出是interface Teacher
        }

        Method[] methods = teacher.getClass().getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method);
            // 树池是：
            // public final boolean com.sun.proxy.$Proxy0.equals(java.lang.Object)
            // public final java.lang.String com.sun.proxy.$Proxy0.toString()
            // public final int com.sun.proxy.$Proxy0.hashCode()
            // public final void com.sun.proxy.$Proxy0.teach()

        }
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
