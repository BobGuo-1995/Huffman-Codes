public class HuffmanTree {
    private Treenode root;

    public HuffmanTree(Treenode node){root=node; }

    public Treenode getRoot() {
        return root;
    }



    public static class Treenode implements Comparable{


        private char data;// just the character
        private Treenode left;
        private Treenode right;
        private int freq; // the frequency





        // create a default constructor
        public Treenode (char datum, int freqcy){
            this(datum, freqcy,null,null);
        }

        public Treenode(char datum,int freqcy,Treenode left,Treenode right){
            // grab from the key of the tree map
            freq=freqcy;
            data=datum;
            this.left=left;
            this.right=right;

        }


        public int getFreq() {
            return freq;
        }

        public Treenode getLeft() {
            return left;
        }

        public Treenode getRight() {
            return right;
        }

        public char getData() {
            return data;
        }



        public int compareTo(Object node){
            if(node instanceof Treenode)
                return freq-((Treenode) node).getFreq();// the first node - second node
                                                        // it is gonna to compare the value of the frequency as in positive, negative or 0
            return 0;

        }

    }
}
