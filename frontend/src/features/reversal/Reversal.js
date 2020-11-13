import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {
  reverseSentence,
  resultText,
  resultClass
} from './reversalSlice';
import styles from './Reversal.module.css';

export function Reversal() {
    const reversedText = useSelector(resultText);
    const reversedTextClass = useSelector(resultClass);
    const dispatch = useDispatch();
    const [sentence, setSentence] = useState('');

    return (
        <div className={styles.box}>
            <div className={styles.container}>
                <textarea id="sentenceInput" name="sentenceInput" rows="4" cols="40" className={styles.textbox}
                    value={sentence}
                    placeholder="Enter sentence here..."
                    onChange={e => setSentence(e.target.value)}
                />
            </div>
            <div className={styles.container}>
                <button id="postSentence" className={styles.button}
                    onClick={() => dispatch(reverseSentence(sentence || "")) }>
                    Reverse it!
                </button>
            </div>
            <div id="result" className={`${styles.container} ${styles[reversedTextClass]}`}>
                {reversedText}
            </div>
        </div>
    );
}
