import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;

public class sample{
    public static void main(String args[]) throws Exception{
        Node node,ndTemp;
        NodeList nodeX;
        int i,j;
        String sv;

        Document document = DocumentBuilderFactory
                                .newInstance()
                                .newDocumentBuilder()
                                .parse(new File(args[0]));
        node = (Node)(document.getDocumentElement());
        System.out.println("\nルートノード名:" + node.getNodeName());
        System.out.println("ルートノードタイプ:" + node.getNodeType());
        System.out.println("その子要素数:" + node.getChildNodes().getLength());
        ndTemp = node.getFirstChild();
        for(i = 1; ndTemp != null; i++, ndTemp = ndTemp.getNextSibling()){
            if(ndTemp.getNodeType() == 3 && check(ndTemp) == false){
                continue;
            }
            else{
                System.out.println(i + "番目の子ノードのノードの名前:" + ndTemp.getNodeName() + "ノードタイプ:" + ndTemp.getNodeType() + "ノード値:\"" + ndTemp.getNodeValue() + "\"");
            }
        }
    }

    static boolean check(Node nd){
        String sv;
        boolean tf;
        int j;

        sv = nd.getNodeValue();
        for(j=0, tf=false; j < sv.length(); j++){
            if(sv.charAt(j) != ' ' && sv.charAt(j) != '\t' && sv.charAt(j) != '\n'){
                tf = true;
                break;
            }
        }
        return tf;
    }
}
