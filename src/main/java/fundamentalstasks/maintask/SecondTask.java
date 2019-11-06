package fundamentalstasks.maintask;

public class SecondTask {
    public static void main(String[] args) {
        int size=args.length-1;

        for(int i=size; i>=0; i--)
            System.out.println("args[" + i + "] : " + args[i]);
    }
}
