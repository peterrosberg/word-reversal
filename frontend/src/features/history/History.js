import React from 'react';
import { useSelector } from 'react-redux';
import {
    history,
    isLoaded,
    isError,
    errorMessage
} from './historySlice';
import styles from './History.module.css';

export function History() {
    const error = useSelector(isError);
    const loaded = useSelector(isLoaded);
    const errorText = useSelector(errorMessage);
    const historyList = useSelector(history);

    if (!loaded) {
        return <div id="latestSentences" className={styles.loading}>Loading history...</div>
    }

    if (error) {
        return <div id="latestSentences" className={styles.error}>Error loading history: {errorText}</div>
    }

    return (
        <div id="latestSentences">
            {historyList.map((element, index) => (<div key={index}>{element}</div>))}
        </div>
    );
}
