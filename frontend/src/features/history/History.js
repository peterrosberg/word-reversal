import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {
    history,
    isLoaded,
    isError,
    errorMessage,
    loadHistory
} from './historySlice';
import moment from 'moment';
import styles from './History.module.css';

export function History() {
    const dispatch = useDispatch();
    const error = useSelector(isError);
    const loaded = useSelector(isLoaded);
    const errorText = useSelector(errorMessage);
    const historyList = useSelector(history);

    useEffect(() => {
        const timer = setTimeout(
            () => dispatch(loadHistory()),
            15000
        );
        return () => clearTimeout(timer);
    });

    if (!loaded) {
        return <div id="latestSentences" className={styles.loading}>Loading history...</div>
    }

    if (error) {
        return <div id="latestSentences" className={styles.error}>Error loading history: {errorText}</div>
    }

    const reversals = historyList.map(
        (element, index) => (
            <div key={index}>
                <div><span className={styles.original}>{element.sentence}</span> => <span>{element.result}</span></div>
                <div className={styles.subLine}>
                    {moment(element.time).format('YYYY-MM-DD HH:mm:ss')}
                </div>
            </div>
        )
    );

    return (
        <div id="latestSentences">
            Recent reversals:
            <div>
                {reversals.length > 0 ? reversals : "No reversals yet!"}
            </div>
        </div>
    );
}
