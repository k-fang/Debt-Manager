package info;

import java.io.IOException;

public interface Saveable {
    public void save() throws IOException;

    public void saveRec() throws IOException;
}
