package framework.cli;

import com.beust.jcommander.IStringConverter;
import org.testng.xml.XmlSuite.ParallelMode;

public class ParallelModeConverter implements IStringConverter<ParallelMode> {

    @Override
    public ParallelMode convert(String s) {
        return ParallelMode.valueOf(s.toUpperCase());
    }
}
