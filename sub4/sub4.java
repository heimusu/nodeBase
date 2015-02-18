import java.io.*;
import org.w3c.dom.*;
import javax.xml.xpath.*;
import javax.xml.parsers.*;
import java.util.*;

public class sub4{
    public static void main(String args[]) throws Exception{
        Node node,ndTemp;
        NodeList nodeX;
        Node att = null;
        int i,j;
        String sv;

        Document document = DocumentBuilderFactory
                                .newInstance()
                                .newDocumentBuilder()
                                .parse(new File(args[0]));
        node = (Node)(document.getDocumentElement());
        FileOutputStream xyz = new FileOutputStream("output.xml");
        OutputStreamWriter out = new OutputStreamWriter(xyz, "utf-8");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();



        System.out.println("\nルートノード名:" + node.getNodeName());
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        out.write(header);
        //属性チェック
        att = attCheck(node,att);
        if(att != null){
            out.write("<" + node.getNodeName() + " " + att +">\n" );
        }
        else{
            out.write("<" + node.getNodeName() + ">\n" );
        }
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
                att = attCheck(ndTemp,att);
                if(att != null){
                    out.write("\t <" + ndTemp.getNodeName() + " " + att + ">");
                }
                else{
                    out.write("\t <" + ndTemp.getNodeName() + ">");
                }
                //文字列が含まれているか？
                Node ndTemp2 = ndTemp.getFirstChild();
                if(ndTemp.getNodeType() == 1 && ndTemp2 != null && ndTemp2.getNodeType() == 3){
                    String output = ndTemp2.getNodeValue();
                    System.out.println(ndTemp2.getNodeType());
                    output = output.replaceAll("\n","");
                    //output = output.replaceAll(" ","");
                    if(output.length() != 0){
                        System.out.println("エレメント内データ:\"" + output + "\"");
                        out.write(output + "</" + ndTemp.getNodeName() + ">\n");
                    }
                }
                //次の要素を見つけに行く
                ndTemp2 = ndTemp2.getNextSibling();
                if(ndTemp2 != null){
                    out.write("\n");
                    for(j = 1; ndTemp2 != null; j++,ndTemp2 = ndTemp2.getNextSibling()){
                        if(ndTemp2.getNodeType() == 3 && check(ndTemp2) == false){
                            continue;
                        }
                        else{
                            System.out.println("ノードの名前:" + ndTemp2.getNodeName() + "ノードタイプ:" + ndTemp2.getNodeType());
                            att = attCheck(ndTemp2,att);
                            if(att != null){
                                out.write("\t\t<" + ndTemp2.getNodeName() + " " + att + ">");
                            }
                            else{
                                out.write("\t\t<" + ndTemp2.getNodeName() + ">");
                            }
                            //文字列が含まれているか？
                            Node ndTemp3 = ndTemp2.getFirstChild();
                            if(ndTemp2.getNodeType() == 1 && ndTemp3 != null && ndTemp3.getNodeType() == 3){
                                String output = ndTemp3.getNodeValue();
                                output = output.replaceAll("\n","");
                                //output = output.replaceAll(" ","");
                                if(output.length() != 0){
                                    System.out.println("エレメント内データ:\"" + output + "\"");
                                    out.write(output + "</" + ndTemp2.getNodeName() + ">\n");
                                }
                            }
                            //4段目までは見つけられるように
                            ndTemp3 = ndTemp3.getNextSibling();
                            if(ndTemp3 != null){
                                out.write("\n");
                                for(j = 1; ndTemp3 != null; j++,ndTemp3 = ndTemp3.getNextSibling()){
                                    if(ndTemp3.getNodeType() == 3 && check(ndTemp3) == false){
                                        continue;
                                    }
                                    else{
                                        System.out.println("ノードの名前:" + ndTemp3.getNodeName() + "ノードタイプ:" + ndTemp3.getNodeType());
                                        att = attCheck(ndTemp3,att);
                                        if(att != null){
                                            out.write("\t\t\t<" + ndTemp3.getNodeName() + " " + att + ">");
                                        }
                                        else{
                                            out.write("\t\t\t<" + ndTemp3.getNodeName() + ">");
                                        }
                                        //文字列が含まれているか？
                                        Node ndTemp4 = ndTemp3.getFirstChild();
                                        if(ndTemp3.getNodeType() == 1 && ndTemp4 != null && ndTemp4.getNodeType() == 3){
                                            String output = ndTemp4.getNodeValue();
                                            output = output.replaceAll("\n","");
                                            //output = output.replaceAll(" ","");
                                            if(output.length() != 0){
                                                System.out.println("エレメント内データ:\"" + output + "\"");
                                                out.write(output + "</" + ndTemp3.getNodeName() + ">\n");
                                            }
                                        }
                                        //5段目までは見つけられるように
                                        ndTemp4 = ndTemp4.getNextSibling();
                                        if(ndTemp4 != null){
                                            out.write("\n");
                                            for(j = 1; ndTemp4 != null; j++,ndTemp4 = ndTemp4.getNextSibling()){
                                                if(ndTemp4.getNodeType() == 3 && check(ndTemp4) == false){
                                                    continue;
                                                }
                                                else{
                                                    System.out.println("ノードの名前:" + ndTemp4.getNodeName() + "ノードタイプ:" + ndTemp4.getNodeType());
                                                    att = attCheck(ndTemp4,att);
                                                    if(att != null){
                                                        out.write("\t\t\t\t<" + ndTemp4.getNodeName() + " " + att + ">");
                                                    }
                                                    else{
                                                        out.write("\t\t\t\t<" + ndTemp4.getNodeName() + ">");
                                                    }
                                                    //文字列が含まれているか？
                                                    Node ndTemp5 = ndTemp4.getFirstChild();
                                                    if(ndTemp4.getNodeType() == 1 && ndTemp5 != null && ndTemp5.getNodeType() == 3){
                                                        String output = ndTemp5.getNodeValue();
                                                        output = output.replaceAll("\n","");
                                                        //output = output.replaceAll(" ","");
                                                        if(output.length() != 0){
                                                            System.out.println("エレメント内データ:\"" + output + "\"");
                                                            out.write(output + "</" + ndTemp4.getNodeName() + ">\n");
                                                        }
                                                    }
                                                }
                                            }
                                            out.write("\t\t\t</" + ndTemp3.getNodeName() + ">\n");
                                        }
                                    }
                                }
                                out.write("\t\t</" + ndTemp2.getNodeName() + ">\n");
                            }
                        }
                    }
                    out.write("\t</" + ndTemp.getNodeName() + ">\n");
                }
            }
        }
        out.write("</" + node.getNodeName() + ">");
        out.close();
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

    //Attribute Checker
    static Node attCheck(Node nd, Node att){
        //ヘッダ部分にAttrが見つかるかどうか
        if(nd.getAttributes() != null){
            NamedNodeMap map = nd.getAttributes();
            att = map.getNamedItem("alt");
        }
        else{
            att = null;
        }
        return att;
    }
}
