import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
/*
Jue,Guo.
Bob
project3
 */
public class main {
    private static double Expectnumber_bit=0.0;
    private static TreeMap<Character,Integer> Check = new TreeMap<>(); // use the tree map to find the similar character
    private static TreeMap<Character,String> map = new TreeMap<>(); // create a new tree map

    private static HuffmanTree ht = new HuffmanTree(null);
    public static void main(String[] args)throws IOException {
        String fileName =chooseFile();
        ReadFile(fileName);
        //pass in the check and remember check(key: character, value: frequency)
        pq(Check);
        //implementing heap by using pirority queue
        setting(ht.getRoot(),"");
        print();


    }

    private static String chooseFile(){
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView());
        jfc.setCurrentDirectory(new File("."));

        int returnValue =jfc.showOpenDialog(null);
        String sourceFileName;
        String sourceFilePathName = " ";

        if(returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile=jfc.getSelectedFile();
            sourceFileName = selectedFile.getName();
            sourceFilePathName = selectedFile.getAbsolutePath();


        }

        return sourceFilePathName;


    }

    private static void ReadFile(String Filepath) {


        BufferedReader br = null;
        FileReader fr = null;
         try {
            fr = new FileReader(Filepath);
            br = new BufferedReader(fr);

            int currentChar; // we want to read char by char


            // we are using a tree map  right here
            while ((currentChar = br.read()) != -1) {

                if (!Check.containsKey((char) (currentChar))) {// check if it is the same character

                    Check.put(((char) currentChar), 1); // cast the char back to char
                } else {
                    Check.put(((char) currentChar), Check.remove((char)currentChar) + 1); // if it is the sane character fre+1


                }
            }

         } catch(IOException e){

             e.printStackTrace();

         }

    }



    private static void pq( TreeMap map ){
        // call piority queue
        PriorityQueue< HuffmanTree.Treenode> list = new PriorityQueue<>();// Name the piority queue list

        // this while loop is used to store all the value into a pirority queue
        while(!map.isEmpty()){
            /*
            I created a new tree-map called entry to store the value from the check
             */
            Map.Entry<Character,Integer> entry = map.pollFirstEntry(); // grab the first value from the root
            list.add(new HuffmanTree.Treenode(entry.getKey(),entry.getValue()));// put everything in the piority queue
        }
        // can not be smaller than one since we are grabbing two value at a time and combine it to one
        while(list.size()>1){

            // pop two values
            HuffmanTree.Treenode node3 = list.poll();
            HuffmanTree.Treenode node4 = list.poll();

            // combining all the new node
            HuffmanTree.Treenode node5 = new HuffmanTree.Treenode((char)0,node3.getFreq()+node4.getFreq(),node3,node4);
            list.add(node5);
        }

        // put the last one in the root
        ht=new HuffmanTree(list.poll());



    }
         // create the binary representation of the character
    public static void setting(HuffmanTree.Treenode currentnode, String c){
        // if the left node is not a leaf, settinng the left connection to 1 and vice versa
        double totalcount=ht.getRoot().getFreq();
        if(currentnode.getLeft()!=null){
            setting(currentnode.getLeft(),c+"1");
        }
        else{
            map.put(currentnode.getData(),c);
            Expectnumber_bit+= c.length()*(currentnode.getFreq()/totalcount);
        }
        if(currentnode.getRight()!=null){

            setting(currentnode.getRight(),c+"0");

        }
    }

    public static void print (){
        for (Map.Entry<Character,String> entry: map.entrySet()){  // using a for each loop to iterate through each item in the tree map: and in that i return tree as a set

            if(entry.getKey()=='\n'){
                System.out.println("character: \\n"+" "+"BIN:"+entry.getValue());
            }
            else if(entry.getKey()=='\r'){
                System.out.println("character: \\R"+" "+"BIN:"+entry.getValue());

            }
            else {

                System.out.println("character: " + entry.getKey() + " " + "BIN:" + entry.getValue());

            }

        }
        System.out.println("The total number of character: "+ht.getRoot().getFreq() );
        System.out.println("The expect: "+Expectnumber_bit);// get the num of the frequency




    }





}


