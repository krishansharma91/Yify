package mvp.dagger.yify.yify.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class StringConverter implements Converter {

    /*
     * In default cases Retrofit calls on GSON which expects a JSON which gives
     * us the following error, com.google.gson.JsonSyntaxException:
     * java.lang.IllegalStateException: Expected BEGIN_OBJECT but was
     * BEGIN_ARRAY at line x column x
     */

    @Override
    public Object fromBody(TypedInput typedInput, Type type)
            throws ConversionException {

        String text = null;
        try {
            text = fromStream(typedInput.in());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public TypedOutput toBody(Object o) {
        return null;
    }

    // Custom method to convert stream from request to string
    public static String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}