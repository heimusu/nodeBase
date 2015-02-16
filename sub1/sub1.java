import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;

public class sub1{
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
            //改行チェック
            if(ndTemp.getNodeType() == 3 && check(ndTemp) == false){
                continue;
            }
            //改行でない場合
            else{
                System.out.println("ノードの名前:" + ndTemp.getNodeName() + "ノードタイプ:" + ndTemp.getNodeType());
                //文字列が含まれているか？
                Node ndTemp2 = ndTemp.getFirstChild();
                if(ndTemp.getNodeType() == 1 && ndTemp2 != null){
                    String output = ndTemp2.getNodeValue();
                    output = output.replaceAll("\n","");
                    output = output.replaceAll(" ","");
                    if(output.length() != 0){
                        System.out.println("エレメント内データ:\"" + output + "\"");
                    }
                }
                //次の要素を見つけに行く
                ndTemp2 = ndTemp2.getNextSibling();
                if(ndTemp2 != null){
                    for(j = 1; ndTemp2 != null; j++,ndTemp2 = ndTemp2.getNextSibling()){
                        if(ndTemp2.getNodeType() == 3 && check(ndTemp2) == false){
                            continue;
                        }
                        else{
                            System.out.println("ノードの名前:" + ndTemp2.getNodeName() + "ノードタイプ:" + ndTemp2.getNodeType());
                            //文字列が含まれているか？
                            Node ndTemp3 = ndTemp2.getFirstChild();
                            if(ndTemp2.getNodeType() == 1 && ndTemp3 != null){
                                String output = ndTemp3.getNodeValue();
                                output = output.replaceAll("\n","");
                                output = output.replaceAll(" ","");
                                if(output.length() != 0){
                                    System.out.println("エレメント内データ:\"" + output + "\"");
                                }
                            }
                        }
                    }
                }
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
