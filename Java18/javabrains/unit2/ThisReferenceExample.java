package unit2;

public class ThisReferenceExample {
    public void doProcess(int i, Process p){
        p.process(i);
    }

    public static void main(String args[]){
        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
        thisReferenceExample.doProcess(10, new Process() {
            @Override
            public void process(int i) {
                System.out.println("value of i is " + i);
                // anonymous inner classes overwrites the this reference
                System.out.println(this);
            }

            @Override
            public String toString() {
                return "This is anonymous inner class";
            }
        });

        thisReferenceExample.doProcess(11,i -> {
            System.out.println("value of i is " + i);
            // error ; lambda does not overwrites the this reference
            // System.out.println(this);
        });

        thisReferenceExample.execute();

    }

    public void execute(){
        doProcess(12,i -> {
            System.out.println("value of i is " + i);
            System.out.println(this);
        });
    }

    @Override
    public String toString() {
        return "This is the ThisReferenceExample class instance";
    }
}
