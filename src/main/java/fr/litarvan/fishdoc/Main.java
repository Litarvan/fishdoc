package fr.litarvan.fishdoc;

import java.io.File;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Swag
 */
public final class Main
{
    public static void main(String[] args)
    {
        // Just for the --sources and --help argument
        OptionParser parser = new OptionParser();
        parser.accepts("sources").withRequiredArg().defaultsTo("src/");
        parser.accepts("packages").withRequiredArg();
        parser.accepts("help");
        parser.allowsUnrecognizedOptions();

        OptionSet options = parser.parse(args);

        if (options.has("help"))
        {
            FishdocRunner.displayHelpAndExit();
        }

        if (!options.has("packages"))
        {
            System.err.println("Missing required --packages option (consider doing fishdoc --help)");
            return;
        }

        String sources = (String) options.valueOf("sources");

        if (!new File(sources).exists())
        {
            System.err.println("Warning : Provided sources path (" + sources + ") is empty");
        }

        try
        {
            com.sun.tools.javadoc.Main.execute(ArrayUtils.addAll(new String[] {
                "-private",
                "-doclet", "fr.litarvan.fishdoc.FishdocRunner",
                "-sourcepath", sources,
                "-subpackages", (String) options.valueOf("packages")
            }, args));
        }
        catch (NoClassDefFoundError e)
        {
            if (e.getMessage().contains("com.sun.tools.javadoc.Main"))
            {
                System.err.println("You need to run Fishdoc with a JDK, because it requires the javadoc tool");
                return;
            }

            e.printStackTrace();
        }
    }
}
