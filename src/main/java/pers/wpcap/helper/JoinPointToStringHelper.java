package pers.wpcap.helper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: JoinPointToStringHelper
 * Created by haisong
 * Description:
 */
public class JoinPointToStringHelper {

    public static String toString(JoinPoint joinPoint) {
        StringBuilder stringBuilder = new StringBuilder();
        appendType(stringBuilder, getType(joinPoint));
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            stringBuilder.append("#");
            stringBuilder.append(methodSignature.getMethod().getName());
            stringBuilder.append("(");
            appendTypes(stringBuilder, methodSignature.getMethod().getParameterTypes());
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    private static Class<?> getType(JoinPoint joinPoint) {
        return Optional.ofNullable(joinPoint.getSourceLocation())
                .map(SourceLocation::getWithinType)
                .orElse(joinPoint.getSignature().getDeclaringType());
    }

    private static void appendTypes(StringBuilder stringBuilder, Class<?>[] types) {
        int size = types.length;
        for (int i = 0;i < size;i++) {
            appendType(stringBuilder, types[i]);
            if (i < size - 1) {
                stringBuilder.append(",");
            }
        }
    }

    private static void appendType(StringBuilder stringBuilder, Class<?> type) {
        if (type.isArray()) {
            appendType(stringBuilder, type.getComponentType());
            stringBuilder.append("[]");
        } else {
            stringBuilder.append(type.getName());
        }
    }

}
