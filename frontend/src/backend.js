export async function postSentence(sentence) {
    try {
        const response = await fetch('/api/reversal', {
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({sentence:sentence}),
            method: 'POST'
        });
        console.log(response);
        if (!response.ok) {
            return { error: "Error " + response.status };
        }
        const responseJson = await response.json();
        console.log(responseJson);
        return responseJson;
    } catch (error) {
        throw new Error('Could not post sentence for reversal!\n' + error.message);
    }
}