public class Command_Injection {

    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer();

        if (args != null) {

            for (String arg : args) {

                sb.append(arg + " ");

            }

        }

        try {

            Runtime.getRuntime().exec(sb.toString());

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

public class Command_Injection_Fix {

    public static void main(String[] args) {

        WindowsCmd wc = new NotepadCmd(args);

        if (wc.isValid()) {

            try {

                Runtime.getRuntime().exec(wc.getCommandLineString());

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}

public interface WindowsCmd {

    String getCommandLineString();

    boolean isValid();

}

public class NotepadCmd implements WindowsCmd {

    private static final String head = "notepad";

    private static final String fileRegex = "[a-zA-Z]:/(([^/\\t\\*<>\\?\\|:\"])+/)*([^/\\t\\*<>\\?\\|:\"])+";

    public NotepadCmd(String[] args) {

        valid = false;

        if (args != null && args.length >= 2) {

            String aName = args[0];

            String fName = args[1];

            aName = aName.toLowerCase();

            if (head.equals(aName)) {
                fName = fName.replaceAll(fileRegex, "");

                if ("".equals(fName)) {

                    fileName = fName;

                    valid = true;

                }

            }

        }

    }

    private String fileName;

    private boolean valid;

    public String getFileName() {

        return fileName;

    }

    public boolean isValid() {

        return valid;

    }

    public String getCommandLineString() {

        return new String(head) + " " + getFileName();

    }

}
