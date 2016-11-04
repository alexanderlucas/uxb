package edu.cwru.mal175.uxb;

/**
 * Created by alexlucas on 9/18/16.
 */
public class StringMessage implements Message {
    private final String string;

    public StringMessage(String string){
        if(string == null){
            this.string = "";
        }

        else {
            this.string = string;
        }
    }

    public String getString() {
        return string;
    }


    @Override
    public boolean equals(Object anObject) {
        if(anObject == null){
            return false;
        }
        else {
            if(anObject instanceof StringMessage) {
                StringMessage object = (StringMessage)anObject;
                return this.getString().equals(object.getString());
            }
            else{
                return false;
            }
        }

    }

    public int length() {
        return this.string.length();
    }

    public char charAt(int index){
        return this.string.charAt(index);
    }

    public boolean contains(CharSequence s) {
        return this.string.contains(s);
    }

    public boolean endsWith(String suffix) {
        return this.string.endsWith(suffix);
    }

    public boolean startsWith(String prefix){
        return this.string.startsWith(prefix);
    }

    public int indexOf(int ch) {
        return this.string.indexOf(ch);
    }

    public int indexOf(int ch, int fromIndex) {
        return this.string.indexOf(ch, fromIndex);
    }

    public int indexOf(String str) {
        return this.string.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return this.string.indexOf(str, fromIndex);
    }

    public int lastIndexOf(int ch) {
        return this.string.lastIndexOf(ch);
    }

    public int lastIndexOf(int ch, int fromIndex) {
        return this.string.lastIndexOf(ch, fromIndex);
    }

    public int lastIndexOf(String str) {
        return this.string.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return this.string.lastIndexOf(str, fromIndex);
    }

    public boolean isEmpty(){
        return this.string.isEmpty();
    }

    public int hashCode(){
        return this.string.hashCode();
    }

    @Override
    public void reach(Device device, Connector connector) {
        device.recv(this, connector);
    }
}

