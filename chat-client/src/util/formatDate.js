const formatDate =  ( date = '' ,  time = ''  ) =>  {
    const dateObj = new Date();
    const months = ['Jan','Feb','Mar','Apr','May','June','July','Aug','Sep','Oct','Nov','Dec'];
    let result = '';
    let [ year , month , day  ] = date.split('-');
    let [ curYear , curMonth , curDay ] = [ dateObj.getFullYear() , dateObj.getMonth() , dateObj.getDate() ];
    let [ hour , min ] = time.split(':');
    let [ curHour , curMin ] = [dateObj.getHours() , dateObj.getMinutes() ];

    let hasYearDiff =  curYear - Number(year) >= 1;
    let hasMonthDiff =  (curMonth+1) - Number(month) >= 0;
    let hasDayDiff =  Math.abs( curDay - Number(day)) > 1;
    let dayDiff =  Math.abs(curDay - Number(day));

    result += hasYearDiff ? year+' ' : '';
    result += hasYearDiff || hasMonthDiff ? months[Number(month) -1 ] : '';
    result += ` ${day} `;
    result += ( hasYearDiff || hasMonthDiff ) ? ' AT ' : '';

    if( !hasDayDiff ){
        if( dayDiff == 1 && curHour >= Number(hour) ){
            result = 'Yesterday';
            return result;
        }else{
            if( dayDiff == 0 &&  (Number(curHour) - Number(hour) < 1 || (curHour - Number(hour) == 1 && ((60 - Math.abs(curMin - Number(min))) < 60 )))){
                result = `${ Number(curHour) - Number(hour) < 1 ? (curMin - Number(min)) : ( 60 - Math.abs(curMin - Number(min))) }`;
                if( (curHour == Number(hour)) &&  curMin == Number(min)) return 'Just Now';
                result +=  Number(result) > 1 ? ' mins ago' : ' min ago';
            }else{
                let resultHour = dayDiff == 1 ? ( 24 - (Math.abs(curHour - Number(hour)))) :  curHour > Number(hour) ? curHour - Number(hour) : ( curHour + Number(hour));
                result = `${resultHour}`;
                result += resultHour > 1 ? ' hrs ago' : ' hr ago';
            }
        }
    }else{
        if( hour == '00' || min == '00') result += `01:00`;
        else result += `${ Number(hour) > 12 ? Number(hour) -12 : hour }:${ min }`;
        result += `${ Number(hour) > 12 ? ' P.M' : ' A.M' }`;
        return result;
    }

    return result;
}

export default formatDate;