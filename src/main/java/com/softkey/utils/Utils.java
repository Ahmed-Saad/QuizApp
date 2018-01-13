package com.softkey.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.internal.util.io.StreamCopier;
import org.primefaces.event.FileUploadEvent;

public class Utils {

    private static Pattern pattern;
    private static Matcher matcher;
    private static String spinnerValue;
    //Email Pattern
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isNotEmpty(List obj) {
        return obj != null && obj.size() != 0;
    }

    public static boolean isNotEmpty(String obj) {
        return obj != null && obj.trim().length() != 0;
    }

    public static boolean isNotEmpty(Object obj) {
        return obj != null;
    }

    public static Boolean isEmptyString(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Long obj) {
        return obj != null && obj.toString().length() != 0;
    }

    public static boolean isNotEmpty(Object[] obj) {
        return obj != null && obj.length != 0;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(List obj) {
        return obj == null || obj.size() == 0;
    }

    public static Boolean isCharacter(Character ch) {
        return true ? (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') : false;
    }

    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getCurrentDate() {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date cal = new Date(System.currentTimeMillis());
        return sdf.format(cal);
    }

    public static String uploadFile(FileUploadEvent event, String type) {
        try {
            ExternalContext extContext
                    = FacesContext.getCurrentInstance().getExternalContext();
            File file = new File(extContext.getRealPath("//WEB-INF//images//"));
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            File result = new File(extContext.getRealPath("//WEB-INF//images//" + type + event.getFile().getFileName()));
            String imageUrl = "faces/WEB-INF/images/" + type + event.getFile().getFileName();
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[StreamCopier.BUFFER_SIZE];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Image uploaded"));
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", e.getMessage()));
            return "";
        }
    }
}
