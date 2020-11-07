package com.pepp.wordreversal.service

import org.springframework.stereotype.Service

@Service
class WordReversalService {

    private val letters = "[A-Za-zÅÄÖåäö]+".toRegex()
    private val characters = "[^A-Za-zÅÄÖåäö]+".toRegex()

    fun reverseWords(input: String): String {

        val words = input.split(characters)
                .map { it.reversed() }

        val delimiters =  input.split(letters)
                .filter { it.isNotEmpty() }

        return zip(input, words, delimiters)
    }


    private fun zip(input: String, words: List<String>, delimiters: List<String>): String {
        return if (words.size > delimiters.size) {
            joinOddListsToString(words, delimiters)
        } else if (delimiters.size > words.size) {
            joinOddListsToString(delimiters, words)
        } else {
            if (startsWithWord(input)) {
                joinEvenListsToString(words, delimiters)
            } else {
                joinEvenListsToString(delimiters, words)
            }
        }
    }

    private fun joinOddListsToString(list1: List<String>, list2: List<String>) =
            joinEvenListsToString(list1, list2).plus(list1.last())

    private fun joinEvenListsToString(list1: List<String>, list2: List<String>) : String =
            list1.zip(list2).joinToString(separator = "") { "${it.first}${it.second}" }

    private fun startsWithWord(input: String) = letters.matches(input.subSequence(0..1))
}