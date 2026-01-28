/**
 * An array analyser, that contains a range of methods that can
 * provide information on the data held in its arrays.
 * @author nilesh kapoor
 * @version 1.0.0
 */
import java.util.*;

public class ArrayAnalyser
{
    private String[ ] data1;
    private String[ ] data2;

    /**
     * Create an ArrayAnalyser.
     * @param data1 An array of data to be analysed.
     * @param data2 An array of data to be analysed.
     */
    public ArrayAnalyser( String[ ] data1, String[ ] data2 )
    {
        this.data1 = data1;
        this.data2 = data2;
    }

    /**
     * Concatenate all strings from both arrays separated by colons.
     * @return A single concatenated String.
     */
    public String concatenate( )
    {
        StringBuilder result = new StringBuilder( );
        for ( String input : data1 )
        {
            result.append( input ).append( ":" );
        }
        for ( int iterator = 0; iterator < data2.length; iterator++ )
        {
            result.append( data2[ iterator ] );
            if ( iterator != data2.length - 1 )
            {
                result.append( ":" );
            }
        }
        return result.toString( );
    }

    /**
     * Find the length of the longest string in both arrays.
     * @return The length of the longest string.
     */
    public int getLongestLength( )
    {
        int maxLength = 0;
        for ( String input : data1 )
        {
            maxLength = Math.max( maxLength, input.length( ) );
        }
        for ( String input2 : data2 )
        {
            maxLength = Math.max( maxLength, input2.length( ) );
        }
        return maxLength;
    }

    /**
     * Find common strings between the two arrays.
     * @return A String array containing common strings.
     */
    public String[ ] getCommonStrings( )
    {
        List< String > commonStrings = new ArrayList< >( );
        for ( String input : data1 )
        {
            if ( Arrays.asList( data2 ).contains( input ) && !commonStrings.contains( input ) )
            {
                commonStrings.add( input );
            }
        }
        return commonStrings.toArray(new String[0]);
    }

    /**
     * Count the number of unique strings that occur only once in total.
     * @return The count of unique strings.
     */
    public int getUniqueCount( )
    {
        Map< String, Integer > frequencyMap = new HashMap< >( );
        for ( String input : data1 )
        {
            frequencyMap.put( input, frequencyMap.getOrDefault( input, 0 ) + 1 );
        }
        for ( String input2 : data2 )
        {
            frequencyMap.put( input2, frequencyMap.getOrDefault( input2, 0 ) + 1 );
        }
        int uniqueCount = 0;
        for ( int count : frequencyMap.values( ) )
        {
            if ( count == 1 )
            {
                uniqueCount++;
            }
        }
        return uniqueCount;
    }

    /**
     * Calculate the product of sums of numerical string values.
     * @return The product of the sums.
     */
    public int product( )
    {
        String[ ] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        Map< String, Integer > numberMap = new HashMap< >( );
        for ( int iterator = 0; iterator < numbers.length; iterator++ )
        {
            numberMap.put( numbers[ iterator ], iterator + 1);
        }

        int sum1 = 0, sum2 = 0;
        for (String input : data1)
        {
            if ( numberMap.containsKey( input ) )
            {
                sum1 += numberMap.get( input );
            }
        }
        for ( String s : data2 )
        {
            if ( numberMap.containsKey( s ) )
            {
                sum2 += numberMap.get( s );
            }
        }
        return sum1 * sum2;
    }

    /**
     * Provide a description of the assessment and module.
     * @return A formatted string.
     */
    @Override
    public String toString( )
    {
        return "This is assessment 3 for module COMP3200";
    }
} 


