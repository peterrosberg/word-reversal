import { configureStore } from '@reduxjs/toolkit';
import reversalReducer from '../features/reversal/reversalSlice';

export default configureStore({
  reducer: {
    reversal: reversalReducer,
  },
});
