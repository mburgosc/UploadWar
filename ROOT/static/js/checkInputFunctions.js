export function sameInput(input1,input2) {
    return input1.value === input2.value;
    
}

export function onlyLettersAndNumbers(input) {
    return (input.value.match("^[a-zA-Z0-9]+$"));
    
}

export function lengthBetween(input,min,max) {
    return (input.value.length > min && input.value.length < max);
}