package XMLCreation;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.nio.file.Files;
import java.nio.file.Path;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class IPaymentPACS008 {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootelement = doc.createElement("Document");
        String ppp = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08";
        String bbb = "http://www.w3.org/2001/XMLSchema-instance";
        rootelement.setAttribute("xmlns", ppp);
        rootelement.setAttribute("xmlns:xsi", bbb);
        doc.appendChild(rootelement);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String TempMsgIdText = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(TempMsgIdText);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd = new SimpleDateFormat("HHmmss");
        String formattedDate = sdf.format(date);
        String OnlyDate = aaa.format(date);
        String currdate = formattedDate.replace(" ", "T");
        String Messagetime1 =ccc.format(date);
        String Messagetime2 =ddd.format(date);
        String Messagetime =Messagetime1+"T"+Messagetime2;
        System.out.println(currdate);
        System.out.println(OnlyDate);
        System.out.println(Messagetime);
        String MsgIdText ="INNDNL2U"+Messagetime ;
        System.out.println(MsgIdText);
        
        //Adding elements to XML
        Element FIToFICstmrCdtTrf = doc.createElement("FIToFICstmrCdtTrf");
        rootelement.appendChild(FIToFICstmrCdtTrf);

        Element GrpHdr = doc.createElement("GrpHdr");
        FIToFICstmrCdtTrf.appendChild(GrpHdr);

        Element MsgId = doc.createElement("MsgId");
        GrpHdr.appendChild(MsgId);
        MsgId.setTextContent(MsgIdText);
        Element CreDtTm = doc.createElement("CreDtTm");
        GrpHdr.appendChild(CreDtTm);
        CreDtTm.setTextContent(currdate);
        Element NbOfTxs = doc.createElement("NbOfTxs");
        GrpHdr.appendChild(NbOfTxs);
        NbOfTxs.setTextContent("1");
        Element TtlIntrBkSttlmAmt = doc.createElement("TtlIntrBkSttlmAmt");
        GrpHdr.appendChild(TtlIntrBkSttlmAmt);
        TtlIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        TtlIntrBkSttlmAmt.setTextContent("1.21");
        Element IntrBkSttlmDt = doc.createElement("IntrBkSttlmDt");
        GrpHdr.appendChild(IntrBkSttlmDt);
        IntrBkSttlmDt.setTextContent(OnlyDate);
        Element SttlmInf = doc.createElement("SttlmInf");
        Element SttlmMtd = doc.createElement("SttlmMtd");
        Element ClrSys = doc.createElement("ClrSys");
        Element Cd = doc.createElement("Cd");

        GrpHdr.appendChild(SttlmInf);
        SttlmInf.appendChild(SttlmMtd);
        SttlmMtd.setTextContent("CLRG");
        SttlmInf.appendChild(ClrSys);
        ClrSys.appendChild(Cd);
        Cd.setTextContent("INC");
        Element PmtTpInf = doc.createElement("PmtTpInf");
        GrpHdr.appendChild(PmtTpInf);
        for (int j = 0; j < 2; j++){

        Element SvcLvl = doc.createElement("SvcLvl");
        Element LclInstrm = doc.createElement("LclInstrm");
        Element Cd1 = doc.createElement("Cd");
    if (j==0)    {

    //String pmtpinfval = "SvcLvl";
    //String cdval = "SEPA";}

    PmtTpInf.appendChild(SvcLvl);
    SvcLvl.appendChild(Cd1);
    Cd1.setTextContent("SEPA");}
    else {
        //String pmtpinf = "LclInstrm";
        //String cdval = "INST";}
            GrpHdr.appendChild(PmtTpInf);
            PmtTpInf.appendChild(LclInstrm);
            LclInstrm.appendChild(Cd1);
            Cd1.setTextContent("INST");
    }}


            Element InstgAgt = doc.createElement("InstgAgt");
            Element FinInstnId = doc.createElement("FinInstnId");
            Element BICFI10 = doc.createElement("BICFI");

            GrpHdr.appendChild(InstgAgt);
            InstgAgt.appendChild(FinInstnId);
            FinInstnId.appendChild(BICFI10);
            BICFI10.setTextContent("EQWLNL2F");

        Element InstdAgt1 = doc.createElement("InstdAgt");
        Element FinInstnId1 = doc.createElement("FinInstnId");
        Element BICFI12 = doc.createElement("BICFI");

        GrpHdr.appendChild(InstdAgt1);
        InstdAgt1.appendChild(FinInstnId1);
        FinInstnId1.appendChild(BICFI12);
        BICFI12.setTextContent("NNBANL2G");



        Element CdtTrfTxInf = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf);
        Element PmtId = doc.createElement("PmtId");
        CdtTrfTxInf.appendChild(PmtId);
        Element InstrId = doc.createElement("InstrId");
        PmtId.appendChild(InstrId);
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd1 = new SimpleDateFormat("HHmmss");
        String formattedDate1 = sdf1.format(date);
        String OnlyDate1 = aaa1.format(date);
        String currdate1 = formattedDate.replace(" ", "T");
        String Messagetime11 =ccc1.format(date);
        String Messagetime21 =ddd1.format(date);
        String Messagetime111 =Messagetime11+"T"+Messagetime21;
        String MsgIdText1 ="INNDNL2U"+Messagetime111 ;
        System.out.println(MsgIdText1);
        InstrId.setTextContent(MsgIdText1);
        Element EndToEndId = doc.createElement("EndToEndId");
        PmtId.appendChild(EndToEndId);
        String EndToEndIdText = "E2E"+Messagetime111;
        EndToEndId.setTextContent(EndToEndIdText);
        Element TxId = doc.createElement("TxId");
        PmtId.appendChild(TxId);
        TxId.setTextContent("pacs.008"+Messagetime111);
        Element IntrBkSttlmAmt = doc.createElement("IntrBkSttlmAmt");
        CdtTrfTxInf.appendChild(IntrBkSttlmAmt);
        IntrBkSttlmAmt.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt.setTextContent("1.21");
        Element AccptncDtTm = doc.createElement("AccptncDtTm");
        CdtTrfTxInf.appendChild(AccptncDtTm);
        AccptncDtTm.setTextContent(currdate1+".063Z");
        Element ChrgBr = doc.createElement("ChrgBr");
        CdtTrfTxInf.appendChild(ChrgBr);
        ChrgBr.setTextContent("SLEV");
        Element UltmtDbtr = doc.createElement("UltmtDbtr");
        CdtTrfTxInf.appendChild(UltmtDbtr);
        Element Id1 = doc.createElement("Id");
        UltmtDbtr.appendChild(Id1);
        Element OrgId = doc.createElement("OrgId");
        Id1.appendChild(OrgId);
        Element AnyBIC = doc.createElement("AnyBIC");
        OrgId.appendChild(AnyBIC);
        AnyBIC.setTextContent("EQWLNL2F");
        Element Dbtr = doc.createElement("Dbtr");
        CdtTrfTxInf.appendChild(Dbtr);
        Element Nm = doc.createElement("Nm");
        Dbtr.appendChild(Nm);
        Nm.setTextContent("name-NL15EQWL0612345678");
        Element PstlAdr = doc.createElement("PstlAdr");
        Dbtr.appendChild(PstlAdr);
        Element Dept = doc.createElement("Dept");
        Element SubDept = doc.createElement("SubDept");
        Element StrtNm = doc.createElement("StrtNm");
        Element BldgNb = doc.createElement("BldgNb");
        Element BldgNm = doc.createElement("BldgNm");
        Element Flr = doc.createElement("Flr");
        Element PstBx = doc.createElement("PstBx");
        Element Room = doc.createElement("Room");
        Element PstCd = doc.createElement("PstCd");
        Element TwnNm = doc.createElement("TwnNm");
        Element TwnLctnNm = doc.createElement("TwnLctnNm");
        Element DstrctNm = doc.createElement("DstrctNm");
        Element CtrySubDvsn = doc.createElement("CtrySubDvsn");
        Element Ctry = doc.createElement("Ctry");
        PstlAdr.appendChild(Dept);
        PstlAdr.appendChild(SubDept);
        PstlAdr.appendChild(StrtNm);
        PstlAdr.appendChild(BldgNb);
        PstlAdr.appendChild(BldgNm);
        PstlAdr.appendChild(Flr);
        PstlAdr.appendChild(PstBx);
        PstlAdr.appendChild(Room);
        PstlAdr.appendChild(PstCd);
        PstlAdr.appendChild(TwnNm);
        PstlAdr.appendChild(TwnLctnNm);
        PstlAdr.appendChild(DstrctNm);
        PstlAdr.appendChild(CtrySubDvsn);
        PstlAdr.appendChild(Ctry);
        Dept.setTextContent("ASDFASD");
        SubDept.setTextContent("ASDFASD");
        StrtNm.setTextContent("EAST STREET");
        BldgNb.setTextContent("BLOG");
        BldgNm.setTextContent("23232");
        Flr.setTextContent("23");
        PstBx.setTextContent("23234");
        Room.setTextContent("1232");
        PstCd.setTextContent("1232");
        TwnNm.setTextContent("CHENNAI");
        TwnLctnNm.setTextContent("ASDFASDFASD");
        DstrctNm.setTextContent("TAMILNADU");
        CtrySubDvsn.setTextContent("SDFASDFAS");
        Ctry.setTextContent("NL");
        Element Id2 = doc.createElement("Id");
        Dbtr.appendChild(Id2);
        Element OrgId1 = doc.createElement("OrgId");
        Id2.appendChild(OrgId1);
        Element LEI = doc.createElement("LEI");
        OrgId1.appendChild(LEI);
        LEI.setTextContent("EQWLNL2FI12345678912");
        Element DbtrAcct = doc.createElement("DbtrAcct");
        CdtTrfTxInf.appendChild(DbtrAcct);
        Element ID3 = doc.createElement("Id");
        DbtrAcct.appendChild(ID3);
        Element IBAN = doc.createElement("IBAN");
        ID3.appendChild(IBAN);
        IBAN.setTextContent("NL15EQWL0612345678");
        Element Prxy = doc.createElement("Prxy");
        DbtrAcct.appendChild(Prxy);
        Element Tp = doc.createElement("Tp");
        Prxy.appendChild(Tp);
        Element Prtry = doc.createElement("Prtry");
        Tp.appendChild(Prtry);
        Prtry.setTextContent("ADKLOPOIUYTREWSAQZXCVBNMJHUYTF12345");
        Element ID4 = doc.createElement("Id");
        Prxy.appendChild(ID4);
        ID4.setTextContent("ASLKDJFASDNASDFASDKFJLKASDDDDDDDFFFFFFFFFFFFFFFFFFFFFFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA3233333333344444444444444444444444444444444444444444444444444444444344");
        Element DbtrAgt = doc.createElement("DbtrAgt");
        CdtTrfTxInf.appendChild(DbtrAgt);
        Element FinInstnId2 = doc.createElement("FinInstnId");
        DbtrAgt.appendChild(FinInstnId2);
        Element BICFI2 = doc.createElement("BICFI");
        FinInstnId2.appendChild(BICFI2);
        BICFI2.setTextContent("EQWLNL2F");
        Element CdtrAgt = doc.createElement("CdtrAgt");
        CdtTrfTxInf.appendChild(CdtrAgt);
        Element FinInstnId3 = doc.createElement("FinInstnId");
        CdtrAgt.appendChild(FinInstnId3);
        Element BICFI3 = doc.createElement("BICFI");
        FinInstnId3.appendChild(BICFI3);
        BICFI3.setTextContent("NNBANL2G");
        Element Cdtr = doc.createElement("Cdtr");
        CdtTrfTxInf.appendChild(Cdtr);
        Element Nm1 = doc.createElement("Nm");
        Cdtr.appendChild(Nm1);
        Nm1.setTextContent("creditor name");
        Element PstlAdr1 = doc.createElement("PstlAdr");
        Cdtr.appendChild(PstlAdr1);
        Element Ctry1 = doc.createElement("Ctry");
        PstlAdr1.appendChild(Ctry1);
        Ctry1.setTextContent("NL");
        Element AdrLine = doc.createElement("AdrLine");
        PstlAdr1.appendChild(AdrLine);
        AdrLine.setTextContent("East Street");
        Element AdrLine1 = doc.createElement("AdrLine");
        PstlAdr1.appendChild(AdrLine1);
        AdrLine1.setTextContent("Chennai");
        Element ID5 = doc.createElement("Id");
        Cdtr.appendChild(ID5);
        Element OrgId2 = doc.createElement("OrgId");
        ID5.appendChild(OrgId2);
        Element AnyBIC1 = doc.createElement("AnyBIC");
        OrgId2.appendChild(AnyBIC1);
        AnyBIC1.setTextContent("NNBANL2G");
        Element CdtrAcct = doc.createElement("CdtrAcct");
        CdtTrfTxInf.appendChild(CdtrAcct);
        Element ID6 = doc.createElement("Id");
        CdtrAcct.appendChild(ID6);
        Element IBAN1 = doc.createElement("IBAN");
        ID6.appendChild(IBAN1);
        IBAN1.setTextContent("NL66NNBA1000000533");
        Element Prxy1 = doc.createElement("Prxy");
        CdtrAcct.appendChild(Prxy1);
        Element Tp1 = doc.createElement("Tp");
        Prxy1.appendChild(Tp1);
        Element Cd4 = doc.createElement("Cd");
        Tp1.appendChild(Cd4);
        Cd4.setTextContent("BC14");
        Element ID7 = doc.createElement("Id");
        Prxy1.appendChild(ID7);
        ID7.setTextContent("ASLKDJFASDNASDFASDKFJLKASDDDDDDDFFFFFFFFFFFFFFFFFFFFFFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA32333333333444444444444444444444444444444444444444444444444444444443");
        Element UltmtCdtr = doc.createElement("UltmtCdtr");
        CdtTrfTxInf.appendChild(UltmtCdtr);
        Element ID8 = doc.createElement("Id");
        UltmtCdtr.appendChild(ID8);
        Element OrgId3 = doc.createElement("OrgId");
        ID8.appendChild(OrgId3);
        Element LEI1 =  doc.createElement("LEI");
        OrgId3.appendChild(LEI1);
        LEI1.setTextContent("EQWLNL2FI12345678912");
        Element RmtInf = doc.createElement("RmtInf");
        CdtTrfTxInf.appendChild(RmtInf);
        Element Ustrd = doc.createElement("Ustrd");
        RmtInf.appendChild(Ustrd);
        Ustrd.setTextContent("Test Carm-029T"+Messagetime111);


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("IPPACS008.xml"));
        transformer.transform(source, result);


    }



}



