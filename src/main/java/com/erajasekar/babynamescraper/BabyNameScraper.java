package com.erajasekar.babynamescraper;

import com.erajasekar.babynamefinder.BabyName;
import com.erajasekar.babynamefinder.Gender;
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

    private static final int NUMBER_TO_FILTER = 1;

    public static void main(String args[]) throws Exception{

       List<BabyName> names = getUrls().flatMap(url -> extractNamesFromUrl(url))
               .filter(name -> !name.isEmpty())
               .map(name -> new BabyName(name, Gender.Boy))
               .filter(babyName -> babyName.getNumerologicalValue().number() == NUMBER_TO_FILTER)
               .collect(Collectors.toList());
       names.forEach(System.out::println);
        System.out.println(names.size());

    }

    private static Stream<String> extractNamesFromUrl(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements nameElements = doc.select("span.Normal-C0");
        Stream<String> result = nameElements.parallelStream().flatMap(
                nameElement -> {
                    List<String> text = Arrays.asList(nameElement.text().split("[(].*?[)]\\s+"));
                    return text.stream();
                }
        )
        .map(name -> name.replaceAll("[(].*[)]", ""));
        //.peek(System.out::println);
        return result;
    }
    private static Stream<String> getUrls(){

        return IntStream.range(97, 97+26).mapToObj(i -> "http://tamils.tamilpower.com/boys-" + (char) i + ".html").parallel();//.peek(System.out::println);


    }
}

