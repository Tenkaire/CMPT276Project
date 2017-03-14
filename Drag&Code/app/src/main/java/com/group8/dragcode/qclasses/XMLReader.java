package com.group8.dragcode.qclasses;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.group8.dragcode.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Liam on 3/12/2017.
 */

public class XMLReader
{
    private Context context;

    public XMLReader(Context context)
    {
        this.context = context;
    }

    public Module getModule(String moduleKey) throws IOException, XmlPullParserException
    {
        XmlResourceParser xml = context.getResources().getXml(R.xml.modules);
        int eventType = xml.next();
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Module") && xml.getAttributeValue(null, "key").equals(moduleKey))
            {
                String moduleName = xml.getAttributeValue(null, "name");
                String moduleCode = xml.getAttributeValue(null, "code");
                boolean needsBrackets = xml.getAttributeBooleanValue(null, "needsBracks", false);
                boolean acceptsArguments = xml.getAttributeBooleanValue(null, "acceptsArgs", false);
                boolean acceptsComparisons = xml.getAttributeBooleanValue(null, "acceptsComps", false);
                xml.close();
                return new Module(context, moduleKey, moduleName, moduleCode, needsBrackets, acceptsArguments, acceptsComparisons);
            }
            eventType = xml.next();
        }
        xml.close();
        return null;
    }

    public Question getQuestion(String questionKey) throws IOException, XmlPullParserException
    {
        XmlResourceParser xml = context.getResources().getXml(R.xml.questions);
        int eventType = xml.next();
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Question") && xml.getAttributeValue(null, "key").equals(questionKey))
            {
                String questionTitle = xml.getAttributeValue(null, "title");
                String questionText = xml.getAttributeValue(null, "text");
                String skeletonCode = xml.getAttributeValue(null, "skelCode");
                String hintText = xml.getAttributeValue(null, "hintText");
                ArrayList<Module> codeModules = new ArrayList<>();

                eventType = xml.next();
                if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Modules"))
                {
                    while (!(eventType == XmlPullParser.END_TAG && xml.getName().equals("Modules")))
                    {
                        if (eventType == XmlPullParser.TEXT)
                        {
                            String moduleKey = xml.getText();
                            Module module = getModule(moduleKey);
                            if (module != null)
                            {
                                codeModules.add(module);
                            }
                        }
                        eventType = xml.next();
                    }
                }

                xml.close();
                return new Question(questionKey, questionTitle, questionText, skeletonCode, hintText, codeModules);
            }
            eventType = xml.next();
        }
        xml.close();
        return null;
    }


}
