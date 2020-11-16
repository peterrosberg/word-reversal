package com.wordsmith.wordreversal.logic

import org.springframework.stereotype.Service

@Service
class WordReversalService(
        properties: ReversalProperties
) {

    private val delimiters = "\\s${properties.delimitingCharacters}"
    private val lettersPattern = "[^$delimiters]+".toRegex()
    private val nonLettersPattern = "[$delimiters]+".toRegex()

    fun reverseWords(input: String): String {

        if (input.isEmpty()) {
            return ""
        }

        val words = input.split(nonLettersPattern)
                .filter { it.isNotEmpty() }
                .map { it.reversed() }

        val nonWords = input.split(lettersPattern)
                .filter { it.isNotEmpty() }

        return if (startsWithWord(input)) {
            zipListsToString(words, nonWords)
        } else {
            zipListsToString(nonWords, words)
        }.trim()
    }

    private fun zipListsToString(list1: List<String>, list2: List<String>): String {
        val result = list1.zip(list2).joinToString(separator = "") { "${it.first}${it.second}" }
        return if (list1.size > list2.size)
            result.plus(list1.last())
        else
            result
    }

    private fun startsWithWord(input: String) = lettersPattern.matches(input.subSequence(0..0))
}