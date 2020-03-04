package me.baijuyi.utility.operation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectReplicator {
    public static void copyFields(Object origin, Object target) {
        Class oClz = origin.getClass();
        Class tClz = target.getClass();
        Field[] ofs = oClz.getDeclaredFields();
        Field[] tfs = tClz.getDeclaredFields();
        Set<String> oFieldNames = Arrays.stream(ofs).map(field -> field.getName()).collect(Collectors.toSet());
        Arrays.stream(tfs).filter(field -> oFieldNames.contains(field.getName())).forEach(field -> {
            try {
                Field toBeCopied = oClz.getDeclaredField(field.getName());
                toBeCopied.setAccessible(true);
                field.setAccessible(true);
                field.set(target, toBeCopied.get(origin));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
    }
}
