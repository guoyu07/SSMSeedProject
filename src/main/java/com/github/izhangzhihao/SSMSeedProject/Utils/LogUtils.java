package com.github.izhangzhihao.SSMSeedProject.Utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 张志豪 (izhangzhihao) &lt;izhangzhihao@hotmail.com&gt;
 */
@Slf4j
public class LogUtils {
    /**
     * 向数据库记录异常信息
     *
     * @param ex 异常
     */
    public static void LogToDB(Exception ex) {

        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        //出错行
        int lineNumber = stackTraceElement.getLineNumber();
        //方法签名
        String methodName = stackTraceElement.getMethodName();
        //获得类名
        String className = stackTraceElement.getClassName();

        ex.printStackTrace();

        log.error("方法" + className + "." + methodName, "参数" + stackTraceElement, "错误行：" + lineNumber, "时间" + new Date(), "异常内容" + ex.toString());
    }
}
