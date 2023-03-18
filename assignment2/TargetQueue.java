package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TargetQueue extends MyQueue<Position> {

    private MyStack<String> parser;

    public TargetQueue() {
        super();
        parser= new MyStack<String>();
    }

    public void clear() {
        super.clear();
    }

    public void addTargets(String strPos) {
        String num = "";
        if(strPos.contains(")(") || strPos.contains("..") || strPos.contains(",,") || strPos.contains("((") || strPos.contains("))")){
            throw new IllegalArgumentException();
        }
        for (char c : strPos.toCharArray()){
            if(!(Character.isDigit(c) || ((int) c== 40) || ((int) c==41) || (int) c==46 || (int) c==44)){throw new IllegalArgumentException();}
            if (c == '(') {
                if (!parser.isEmpty()) {throw new IllegalArgumentException();}
                else{
                    parser.push("(");}
                }
            if (Character.isDigit(c)) {
                num = num + c;
            }
            if (c == ',') {
                if (num.equals("")) {
                    throw new IllegalArgumentException();
                } else {
                    parser.push(num);
                    parser.push(",");
                    num = "";}
            }
            if (c == ')') {

                if(!(parser.getSize()>=3) || num.equals("")){throw new IllegalArgumentException();}

                String comma= parser.pop(); String x= parser.pop();String left=parser.pop();

                if(!(comma.equals(",")) && !(x.matches("[0-9]+")) && !(left.equals("("))){
                    throw new IllegalArgumentException();}

                Position pos= new Position(Integer.parseInt(x),Integer.parseInt(num));
                this.enqueue(pos);
                parser.clear();
                num="";
            }
            if(c=='.'){if(!num.equals("") && !parser.isEmpty()){throw new IllegalArgumentException();}
            }
        }
        if(!parser.isEmpty() || !num.equals("")){throw new IllegalArgumentException();}
    }

    public static void main(String[]args){
        TargetQueue test = new TargetQueue();
        test.addTargets("(7,2).(5,6)");




    }
}



