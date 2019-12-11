public class Main {
    private static String START_TOKEN = "<wsse:UsernameToken";
    private static String END_TOKEN = "</wsse:UsernameToken";
    private static String START_P_PASS = "<ns1:paymentPassword>";
    private static String END_P_PASS = "</ns1:paymentPassword>";

    public static void main(String[] args) {
        String str = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Header><wsse:Security soap:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">***private data***/wsse:UsernameToken></wsse:Security></soap:Header><soap:Body><ns1:FindAccountByIdRequest xmlns:ns1=\"http://www.moneta.ru/schemas/messages.xsd\">48081051</ns1:FindAccountByIdRequest></soap:Body></soap:Envelope>";
        log(str);
    }


    public static void log(String str) {
        System.out.println("------------- out --------------");
        StringBuilder builder = new StringBuilder(str);
        // here comes my xml:
        String soapXml = builder.toString();
        int startToken = soapXml.indexOf(START_TOKEN);
        int endToken = soapXml.indexOf(END_TOKEN);
        String logXml = builder.substring(0, startToken)
                + "***private data***"
                + builder.substring(endToken + 1);
        if (logXml.contains(START_P_PASS) && logXml.contains(END_P_PASS)) {
            int startPass = soapXml.indexOf(START_P_PASS);
            int endPass = soapXml.indexOf(END_P_PASS);
            logXml = logXml.substring(0, startPass)
                    + "***private data***"
                    + logXml.substring(endPass + 1);
        }
        System.out.println("Output: " + logXml);

        System.out.println("------------- out end --------------");
    }

    boolean stringsRearrangement(String[] inputArray) {
        int n = inputArray.length;
        return permute(inputArray, 0, n-1);
    }

    boolean permute(String[] arr, int l, int r) {
        boolean complete = true;

        if (l == r) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (!check(arr[i], arr[i + 1])) {
                    complete = false;
                    break;
                }
            }
        } else {
            for (int i = l; i <= r; i++) {

                arr = swap(arr, l, i);
                permute(arr, l + 1, r);
                arr = swap(arr, l, i);

            }
        }
        return complete;
    }


    String[] swap(String[] arr, int i, int j) {
        String t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        return arr;
    }

    boolean check(String a, String b) {
        int k = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                k++;
            }

        }
        if (k != 1) {
            return false;
        }
        return true;
    }


}
