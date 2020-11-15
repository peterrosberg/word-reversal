import { createSlice } from '@reduxjs/toolkit';
import { postSentence } from '../../backendClient'

export const reversalSlice = createSlice({
    name: 'reversal',
    initialState: {
        value: '<reversed sentence will appear here>',
        resultState: 'placeholder'
    },
    reducers: {
        setText: (state, action) => {
            if (action.payload.result) {
                const result = action.payload.result;
                state.value = result !== '' ? result : '<reversed sentence will appear here>';
                state.resultState = result !== '' ? 'success' : 'placeholder';
            } else {
                state.value = action.payload.error;
                state.resultState = 'error';
            }
        },
    },
});

export const { setText } = reversalSlice.actions;

export const reverseSentence = sentence => dispatch => {
    postSentence(sentence).then(result => dispatch(setText(result)));
};

export const resultText = state => state.reversal.value;
export const resultClass = state => state.reversal.resultState;

export default reversalSlice.reducer;
