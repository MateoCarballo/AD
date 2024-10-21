import java.io.File;
import java.io.FilenameFilter;

public class ComprobarExtension implements FilenameFilter {

    private String extension;

    ComprobarExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(this.extension);
    }
}
