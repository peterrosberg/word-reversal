import { configureStore } from '@reduxjs/toolkit';
import reversalReducer from '../features/reversal/reversalSlice';
import historyReducer from '../features/history/historySlice';

export default configureStore({
    reducer: {
        reversal: reversalReducer,
        history: historyReducer,
    },
});
