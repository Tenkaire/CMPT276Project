package com.group8.dragcode.qclasses;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

    public Question getQuestion(String questionKey) throws IOException, XmlPullParserException
    {
        XmlResourceParser xml = context.getResources().getXml(R.xml.questions);
        int eventType = xml.next();
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Question") && xml.getAttributeValue(null, "key").equals(questionKey))
            {
                String questionTitle = xml.getAttributeValue(null, "title");
                String questionDescription = xml.getAttributeValue(null, "description");
                String hintText = xml.getAttributeValue(null, "hintText");
                String language = xml.getAttributeValue(null, "language");
                String difficulty = xml.getAttributeValue(null, "difficulty");
                ArrayList<Answer> codeAnswers = new ArrayList<>();
                ArrayList<LinearLayout> codeRows = new ArrayList<>();
                ArrayList<String> codeOutputs = new ArrayList<>();
                LinearLayout tableRow = new LinearLayout(context);
                tableRow.setOrientation(LinearLayout.HORIZONTAL);

                eventType = xml.next();
                if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Code"))
                {
                    while (!(eventType == XmlPullParser.END_TAG && xml.getName().equals("Code")))
                    {
                        if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Text"))
                        {
                            String tabs = "";
                            for (int numTabs = xml.getAttributeIntValue(null, "indentLevel", 0); numTabs > 0; numTabs--)
                            {
                                tabs = tabs + "\t\t";
                            }

                            eventType = xml.next();
                            if (eventType == XmlPullParser.TEXT)
                            {
                                TextView textView = new TextView(context);
                                textView.setText(tabs + xml.getText());
                                tableRow.addView(textView);
                            }
                        }

                        if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Blank"))
                        {
                            Blank blank = new Blank(context);
                            blank.setAnswerKey(xml.getAttributeValue(null, "answerKey"));
                            tableRow.addView(blank);
                        }

                        if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Newline"))
                        {
                            codeRows.add(tableRow);
                            tableRow = new TableRow(context);
                            tableRow.setOrientation(LinearLayout.HORIZONTAL);
                        }

                        eventType = xml.next();
                    }

                    if (tableRow.getChildCount() > 0)
                    {
                        codeRows.add(tableRow);
                    }
                }

                eventType = xml.next();
                if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Output"))
                {
                    while (!(eventType == XmlPullParser.END_TAG && xml.getName().equals("Output")))
                    {
                        if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Text"))
                        {
                            eventType = xml.next();
                            if (eventType == XmlPullParser.TEXT)
                            {
                                codeOutputs.add(xml.getText());
                            }
                        }

                        eventType = xml.next();
                    }
                }

                eventType = xml.next();
                if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Answers"))
                {
                    while (!(eventType == XmlPullParser.END_TAG && xml.getName().equals("Answers")))
                    {
                        String answerKey = "";
                        String answerText = "";

                        if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Answer"))
                        {
                            Answer answer = new Answer(context);
                            answerKey = xml.getAttributeValue(null, "key");

                            eventType = xml.next();
                            if (eventType == XmlPullParser.TEXT)
                            {
                                answerText = xml.getText();
                            }

                            answer.setAnswerKey(answerKey);
                            answer.setAnswerText(answerText);
                            codeAnswers.add(answer);
                        }

                        eventType = xml.next();
                    }
                }

                xml.close();
                return new Question(questionKey, questionTitle, questionDescription, hintText, language, difficulty, codeAnswers, codeRows, codeOutputs);
            }
            eventType = xml.next();
        }
        xml.close();
        return null;
    }

    public ArrayList<String> getQuestionKeys() throws IOException, XmlPullParserException
    {
        ArrayList<String> returnKeys = new ArrayList<>();

        XmlResourceParser xml = context.getResources().getXml(R.xml.questions);
        int eventType = xml.next();
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            if (eventType == XmlPullParser.START_TAG && xml.getName().equals("Question"))
            {
                String questionKey = xml.getAttributeValue(null, "key");
                returnKeys.add(questionKey);
            }
            eventType = xml.next();
        }
        xml.close();
        return returnKeys;
    }


}
