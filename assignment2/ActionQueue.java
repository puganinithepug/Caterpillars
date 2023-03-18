package assignment2;

public class ActionQueue extends MyQueue<Direction> {

    private MyQueue<String> dir;

    public ActionQueue() {
        this.dir=new MyQueue<>();
    }

    public void clear() {
        super.clear();
    }

    public void loadFromEncodedString(String s) {
        String string=a(s, "");

        for(char c: string.toCharArray()){

            if(c=='N'){
                enqueue(Direction.valueOf("NORTH"));
            }
            if(c=='S'){
                enqueue(Direction.valueOf("SOUTH"));
            }
            if(c=='W'){
                enqueue(Direction.valueOf("WEST"));
            }
            if(c=='E'){
                enqueue(Direction.valueOf("EAST"));
            }
        }
    }

    public static int getIndexOfUnit(String s) {
            int unit1 = 0;
            int unit2= 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '[') {
                    unit1++;
                }
                if (s.charAt(j) == ']') {
                    unit2++;
                }

                if (unit1== unit2) {
                    return j;
                }
            }

            throw new IllegalArgumentException();
        }

    public String a(String s, String ans) {

        for (int i = 0; i < s.length(); i++) {

            if(s.indexOf('0')==0){this.clear(); break;}

            if(s.indexOf('0')>0 && !Character.isDigit(s.charAt(s.indexOf('0')-1))){this.clear(); break;}


            if((s.contains("[") && !s.contains("]")) || (s.contains("]") && !s.contains("["))){throw new IllegalArgumentException();}

            if(Character.isAlphabetic(s.charAt(i)) && !(s.charAt(i)=='N' || s.charAt(i)=='S' || s.charAt(i) == 'W' || s.charAt(i) == 'E')){
                throw new IllegalArgumentException();
            }

            if(Character.isAlphabetic(s.charAt(i)) && i!=0 &&
                    !(s.charAt(i-1)=='[' || Character.isAlphabetic(s.charAt(i-1)) || Character.isDigit(s.charAt(i-1)) ) &&
                    !(Character.isAlphabetic(s.charAt(i+1)) || Character.isDigit(s.charAt(i+1)) || s.charAt(i+1)!=']')){
                throw new IllegalArgumentException();
            }

            if(Character.isAlphabetic(s.charAt(i)) && i==0 && !(Character.isAlphabetic(s.charAt(i+1))
                    || Character.isDigit(s.charAt(i+1)) || s.charAt(i+1)!='[')){
                throw new IllegalArgumentException();
            }

            if (Character.isDigit(s.charAt(i))) {
                String num = "" + s.charAt(i);
                i++;
                while (Character.isDigit(s.charAt(i))) {
                    num += "" + s.charAt(i);
                    i++;
                }

                if (s.charAt(i) != '[') {
                    throw new IllegalArgumentException();
                }
                for (int x = 0; x < Integer.parseInt(num) - 1; x++) {
                    ans = a(s.substring(i, i + getIndexOfUnit(s.substring(i)) + 1), ans);
                }
            } else if (s.charAt(i) == 'N' || s.charAt(i) == 'E' || s.charAt(i) == 'W' || s.charAt(i) == 'S') {
                ans += s.charAt(i);

            } else if (!(s.charAt(i) == ']' || s.charAt(i) == '[')) {
                throw new IllegalArgumentException();
            }
            else if(s.charAt(i)==']'&& s.charAt(i-1)=='['){
                throw new IllegalArgumentException();
            }
            else if(s.charAt(i)=='['&& s.charAt(i+1)=='['){
                throw new IllegalArgumentException();
            }
        }
        return ans;
    }


    public MyQueue<String> getDir(){
        return this.dir;
    }

    public static void main(String[]args){

        ActionQueue q = new ActionQueue();

        q.loadFromEncodedString("0[E]");


        System.out.print(q.isEmpty());



    }
//    package assignment2;
//
//public class ActionQueue extends MyQueue<Direction> {
//
//    private MyQueue<String> dir;
//
//    public ActionQueue() {
//        this.dir=new MyQueue<>();
//    }
//
//    public void clear() {
//        super.clear();
//    }
//
//    public void loadFromEncodedString(String s) {
//        String string=a(s, "");
//
//        for(char c: string.toCharArray()){
//            if(c=='N'){
//                enqueue(Direction.valueOf("NORTH"));
//            }
//            if(c=='S'){
//                enqueue(Direction.valueOf("SOUTH"));
//            }
//            if(c=='W'){
//                enqueue(Direction.valueOf("WEST"));
//            }
//            if(c=='E'){
//                enqueue(Direction.valueOf("EAST"));
//            }
//        }
//    }
//
//    public static int getIndexOfUnit(String s) {
//        int unit = 0;
//        for(int j = 0; j < s.length(); j++) {
//            if(s.charAt(j) == '[') {
//                unit ++;
//            }
//            if(s.charAt(j) == ']') {
//                unit ++;
//            }
//            if(unit%2==0 && s.indexOf(']')!=-1) {
//                return j;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//    public static String a(String s, String ans) {
//
//        for (int i = 0; i < s.length(); i++) {
//            if (Character.isDigit(s.charAt(i))) {
//                String num = "" + s.charAt(i);
//                i++;
//                while (Character.isDigit(s.charAt(i))) {
//                    num += "" + s.charAt(i);
//                    i++;
//                }
//
//                if (s.charAt(i) != '[') {
//                    throw new IllegalArgumentException();
//                }
//                for (int x = 0; x < Integer.parseInt(num) - 1; x++) {
//                    ans = a(s.substring(i, i + getIndexOfUnit(s.substring(i)) + 1), ans);
//                }
//            } else if (s.charAt(i) == 'N' || s.charAt(i) == 'E' || s.charAt(i) == 'W' || s.charAt(i) == 'S') {
//                ans += s.charAt(i);
//            } else if (!(s.charAt(i) == ']' || s.charAt(i) == '[')) {
//                throw new IllegalArgumentException();
//            }
//        }
//        return ans;
//    }
//
//
//    public MyQueue<String> getDir(){
//        return this.dir;
//    }
//
//    public static void main(String[]args){
//
//        ActionQueue q = new ActionQueue();
//
//        q.loadFromEncodedString("3[4[N]5[NW]]2[NE]");
//
//        System.out.print(q);
//
//    }
//}
}