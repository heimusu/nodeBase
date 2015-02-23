//演習4-2のプログラム

import java.io.*;
import org.w3c.dom.*;
import javax.xml.xpath.*;
import javax.xml.parsers.*;
import java.util.*;
import java.util.regex.*;

public class sub5{
    public static void main(String args[]) throws Exception{
        Node node,ndTemp;
        NodeList nodeX;
        Node att = null;
        int i,j;
        String sv;
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;

        //検索する文字列
        String input = "This work may be freely copied and distributed worldwide.";

        Document document = DocumentBuilderFactory
                                .newInstance()
                                .newDocumentBuilder()
                                .parse(new File(args[0]));
        node = (Node)(document.getDocumentElement());
        FileOutputStream xyz = new FileOutputStream("output.xml");
        OutputStreamWriter out = new OutputStreamWriter(xyz, "utf-8");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        /*
        //単体のみ
        //XPathパーサー
        String filePath = "caesar.xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(filePath));
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xp = xpf.newXPath();
        XPathExpression xpe1 = xp.compile("/PLAY/FM/P/text()");
        String s1 = (String)xpe1.evaluate(doc, XPathConstants.STRING);
        System.out.println(s1);
        */

        String stArray[] = new String[10];
        String pass = "/PLAY/FM/P";
        stArray[0] = equParse(pass,1);  //ルートノード
        stArray[1] = equParse(pass,2);
        stArray[2] = equParse(pass,3);

        //System.out.println("\nルートノード名:" + node.getNodeName());
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
        //パス式チェック
        if(stArray[0].equals(node.getNodeName())){
            //System.out.println(node.getNodeName());
            flag1 = 1;
        }
        //System.out.println("ルートノードタイプ:" + node.getNodeType());
        //System.out.println("その子要素数:" + node.getChildNodes().getLength());
        ndTemp = node.getFirstChild();
        for(i = 1; ndTemp != null; i++, ndTemp = ndTemp.getNextSibling()){
            //改行チェック
            if(ndTemp.getNodeType() == 3 && check(ndTemp) == false){
                continue;
            }
            //改行でない場合
            else{
                //System.out.println("ノードの名前:" + ndTemp.getNodeName() + "ノードタイプ:" + ndTemp.getNodeType());
                att = attCheck(ndTemp,att);
                if(att != null){
                    out.write("\t <" + ndTemp.getNodeName() + " " + att + ">");
                }
                else{
                    out.write("\t <" + ndTemp.getNodeName() + ">");
                }
                //パス式チェック
                if(stArray[1].equals(ndTemp.getNodeName())){
                    //System.out.println(ndTemp.getNodeName());
                    flag2 = 1;
                }
                //文字列が含まれているか？
                Node ndTemp2 = ndTemp.getFirstChild();
                if(ndTemp.getNodeType() == 1 && ndTemp2 != null && ndTemp2.getNodeType() == 3){
                    String output = ndTemp2.getNodeValue();
                    //System.out.println(ndTemp2.getNodeType());
                    output = output.replaceAll("\n","");
                    //output = output.replaceAll(" ","");
                    if(output.length() != 0){
                        //System.out.println("エレメント内データ:\"" + output + "\"");
                        out.write(output + "</" + ndTemp.getNodeName() + ">\n");
                        if(output.equals(input)){
                            System.out.println("/" + node.getNodeName() + "/" + ndTemp.getNodeName());
                        }
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
                            //System.out.println("ノードの名前:" + ndTemp2.getNodeName() + "ノードタイプ:" + ndTemp2.getNodeType());
                            att = attCheck(ndTemp2,att);
                            if(att != null){
                                out.write("\t\t<" + ndTemp2.getNodeName() + " " + att + ">");
                            }
                            else{
                                out.write("\t\t<" + ndTemp2.getNodeName() + ">");
                            }
                            //パス式チェック
                            if(stArray[2].equals(ndTemp2.getNodeName())){
                                //System.out.println(ndTemp2.getNodeName());
                                flag3 = 1;
                            }
                            //文字列が含まれているか？
                            Node ndTemp3 = ndTemp2.getFirstChild();
                            if(ndTemp2.getNodeType() == 1 && ndTemp3 != null && ndTemp3.getNodeType() == 3){
                                String output = ndTemp3.getNodeValue();
                                output = output.replaceAll("\n","");
                                if(flag1 == 1 && flag2 == 1 && flag3 == 1){
                                    //System.out.println(output);
                                    flag3 = 0;
                                }
                                //output = output.replaceAll(" ","");
                                if(output.length() != 0){
                                    //System.out.println("エレメント内データ:\"" + output + "\"");
                                    out.write(output + "</" + ndTemp2.getNodeName() + ">\n");
                                    if(output.equals(input)){
                                        System.out.println("/" + node.getNodeName() + "/" + ndTemp.getNodeName() + "/" + ndTemp2.getNodeName());
                                    }
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
                                        //System.out.println("ノードの名前:" + ndTemp3.getNodeName() + "ノードタイプ:" + ndTemp3.getNodeType());
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
                                                //System.out.println("エレメント内データ:\"" + output + "\"");
                                                out.write(output + "</" + ndTemp3.getNodeName() + ">\n");
                                                if(output.equals(input)){
                                                    System.out.println("/" + node.getNodeName() + "/" + ndTemp.getNodeName() + "/" + ndTemp2.getNodeName() + "/" + ndTemp3.getNodeName());
                                                }
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
                                                    //System.out.println("ノードの名前:" + ndTemp4.getNodeName() + "ノードタイプ:" + ndTemp4.getNodeType());
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
                                                            //System.out.println("エレメント内データ:\"" + output + "\"");
                                                            out.write(output + "</" + ndTemp4.getNodeName() + ">\n");
                                                if(output.equals(input)){
                                                    System.out.println("/" + node.getNodeName() + "/" + ndTemp.getNodeName() + "/" + ndTemp2.getNodeName() + "/" + ndTemp3.getNodeName() + "/" + ndTemp4.getNodeName());
                                                }
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

    static String equParse(String pass,int depth){
        int times = 1;
        int i,j;
        char mem;
        String result = null;

        for(i = 1,j = 1; i < pass.length(); i++){
            mem = pass.charAt(i);
            if(mem == '/'){
                result = pass.substring(j,i);
                j = i + 1;
                if(depth == times){
                    break;
                }
                times++;
            }
            else if(i == (pass.length()-1)){
                result = pass.substring(j,i+1);
            }
        }

        return result;
    
    }
}
