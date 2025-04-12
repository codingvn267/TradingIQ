import React, { useEffect, useState } from 'react';
import axios from 'axios';

const NewsPage = () => {
  const [news, setNews] = useState([]);

  useEffect(() => {
    axios.get('https://api.marketaux.com/v1/news/all', {
      params: {
        api_token: '582UvRgevpzSkrOsUuTtb1VfHBFlQ3YJ09kl6Dxz', 
        language: 'en',
        filter_entities: 'true',
        countries: 'us'
      }
    })
    .then(response => {
      setNews(response.data.data);
    })
    .catch(error => {
      console.error('Error fetching news:', error);
    });
  }, []);

  return (
    <div className="news-container panel-box">
      <ul>
        {news.slice(0, 5).map((item) => (
          <li key={item.uuid}>
            <a href={item.url} target="_blank" rel="noreferrer">{item.title}</a>
            <p style={{ fontSize: '0.9rem', color: '#ccc' }}>{item.snippet}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default NewsPage;
