package com.erajasekar.babynamescraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by relango on 4/5/15.
 */
public class BabyNameScraper {

   // private static final Pattern namePattern = Pattern.compile("(.*)[(].*[)]");

    public static void main(String args[]) throws Exception{

       getUrls().map(url -> extractNamesFromUrl(url)).collect(Collectors.toList());
      /*  Document doc = Jsoup.connect("http://tamils.tamilpower.com/boys-a.html").get();
        Elements nameElements = doc.select("span.Normal-C0");
        List<String> result = nameElements.parallelStream().flatMap(
                nameElement -> {
                    List<String> text = Arrays.asList(nameElement.text().split("[(].*?[)]\\s+"));
                    //   System.out.println(nameElement.text());
                    return text.stream();
                }
        ).map(name -> name.replaceAll("[(].*[)]", "")).collect(Collectors.toList());
        System.out.println(result.size());
        System.out.println(result);*/
    }

   /* private static Function<String, List<String>> extractName(String text){
        List<String> result = Arrays.asList(text.split("\\s+"));
        return result;
    }*/

    private static Stream<String> extractNamesFromUrl(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements nameElements = doc.select("span.Normal-C0");
      //  System.out.println(nameElements);
        Stream<String> result = nameElements.stream().peek(System.out::println).flatMap(
                nameElement -> {
                    System.out.println(nameElement);
                    List<String> text = Arrays.asList(nameElement.text().split("[(].*?[)]\\s+"));

                    return text.stream();
                }
        )
        //.map(name -> name.replaceAll("[(].*[)]", ""))
        .peek(System.out::println);
        return result;
    }
    private static Stream<String> getUrls(){

        return IntStream.range(97, 98).mapToObj(i -> "http://tamils.tamilpower.com/boys-" + (char) i + ".html").peek(System.out::println);


    }
}

