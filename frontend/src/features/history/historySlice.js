import { createSlice } from '@reduxjs/toolkit';
import { getLatest } from '../../backend'

export const historySlice = createSlice({
    name: 'history',
    initialState: {
        history: [],
        isLoaded: false,
        errorMessage: '',
        isError: false
    },
    reducers: {
        setHistory: (state, action) => {
            console.log(action.payload)
            state.isLoaded = true;
            if (action.payload.error) {
                state.isError = true;
                state.errorMessage = action.payload.error;
            } else {
                state.history = action.payload;
                state.isError = false;
            }
        },
    },
});

export const { setHistory } = historySlice.actions;

export const loadHistory = sentence => dispatch => {
    getLatest(sentence).then(result => dispatch(setHistory(result)));
};

export const history = state => state.history.history;
export const isLoaded = state => state.history.isLoaded;
export const isError = state => state.history.isError;
export const errorMessage = state => state.history.errorMessage;

export default historySlice.reducer;
