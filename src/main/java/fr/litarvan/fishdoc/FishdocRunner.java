package fr.litarvan.fishdoc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.javadoc.RootDoc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Olalaa
 */
public class FishdocRunner
{
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static boolean start(RootDoc root)
    {
        File projectFile = new File(System.getProperty("user.dir"), "fishdoc.json");
        File output = new File(System.getProperty("user.dir"), "build/");

        String name = null;
        String version = null;
        String icon = null;

        for (String[] couple : root.options())
        {
            switch (couple[0])
            {
                case "--name":
                    name = couple[1];
                    break;
                case "--version":
                    version = couple[1];
                    break;
                case "--icon":
                    icon = couple[1];
                    break;
                case "--output":
                    output = new File(couple[1]);
                    break;
                case "--project":
                    projectFile = new File(couple[1]);
                    break;
            }
        }

        FishdocProject project = null;

        if (projectFile.exists())
        {
            try
            {
                project = gson.fromJson(new BufferedReader(new FileReader(projectFile)), FishdocProject.class);
            }
            catch (IOException e)
            {
                System.err.println("Couldn't read file '" + projectFile + "' : " + e);
                return false;
            }

            if (name != null)
            {
                project.setName(name);
            }

            if (version != null)
            {
                project.setVersion(version);
            }

            if (icon != null)
            {
                project.setIcon(icon);
            }

            if (project.getName() == null || project.getVersion() == null)
            {
                System.err.println("Project file is missing 'name' or/and 'version' field");
                return false;
            }
        }

        if (project == null)
        {
            if (name == null || version == null)
            {
                System.err.println("Project file doesn't exist => At least --name and --version are required");
                return false;
            }

            project = new FishdocProject(name, version, icon);
        }

        System.out.println();

        Fishdoc fishdoc = new Fishdoc(project, root, output);
        fishdoc.start();

        return true;
    }

    public static int optionLength(String option)
    {
        switch (option)
        {
            case "--packages":
            case "--output":
            case "--sources":
            case "--project":
            case "--name":
            case "--version":
            case "--icon":
                return 2;
        }

        return 1;
    }

    public static void displayHelpAndExit()
    {
        System.out.println("Usage : fishdoc [OPTIONS] --packages <packages to include>");
        System.out.println("");
        System.out.println("Arguments starting with - are given to javadoc, the one starting with -- are given to fishdoc");
        System.out.println("    --packages <packageslist>  (Recursive) Packages to include in the documentation");
        System.out.println("    --output  <path>           HTML output directory");
        System.out.println("    --sources <path>           Path of the directory where the .java sources are");
        System.out.println("    --project <path>           Path to the fishdoc.json file (default to ./fishdoc.json)");
        System.out.println("    --name <name>              Provide project name");
        System.out.println("    --version <version>        Provide project version");
        System.out.println("    --icon <path>              Provide project icon");
        System.out.println("");
        System.out.println("    -public                    Show only public classes and members");
        System.out.println("    -protected                 Show protected/public classes and members (default)");
        System.out.println("    -private                   Show all classes and members");
        System.out.println("    -sourcepath <pathlist>     Specify where to find source files");
        System.out.println("    -cp, -classpath <pathlist> Specify where to find user class files");
        System.out.println("    -exclude <pkglist>         Specify a list of packages to exclude");
        System.out.println("    -breakiterator             Specify subpackages to recursively load");
        System.out.println("    -bootclasspath <pathlist>  Override location of class files loaded by the bootstrap class loader");
        System.out.println("    -source <release>          Provide source compatibility with specified release");
        System.out.println("    -extdirs <dirlist>         Override location of installed extensions");
        System.out.println("    -verbose                   Output messages about what Javadoc is doing");
        System.out.println("    -locale <name>             Locale to be used, e.g. en_US or en_US_WIN");
        System.out.println("    -encoding <name>           Source file encoding name");
        System.out.println("    -quiet                     Do not display status messages");
        System.out.println("    -J<flag>                   Pass <flag> directly to the runtime system");
        System.out.println("    -X                         Print a synopsis of nonstandard options and exit");
        System.exit(0);
    }
}
