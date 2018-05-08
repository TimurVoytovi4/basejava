package webapp;

public class LazySingletone {
    volatile private static LazySingletone INSTANCE;

    private LazySingletone() {
    }

    private static class LazySingletonHolder {
        private static final LazySingletone INSTANCE = new LazySingletone();
    }

    public static LazySingletone getInstance() {
        return LazySingletonHolder.INSTANCE;
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
    }
}