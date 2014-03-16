import java.lang.reflect.Method;


/** From Stackoverflow:
    
  @see <a href="http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method">http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method</a>

 */


public class TraceHelper {
    // save it static to have it available on every call
    private static Method m;

    static {
        try {
            m = Throwable.class.getDeclaredMethod("getStackTraceElement",
						  int.class);
            m.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMethodName(final int depth) {
        try {
            StackTraceElement element = (StackTraceElement) m.invoke(
								     new Throwable(), depth + 1);
            return element.getMethodName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}